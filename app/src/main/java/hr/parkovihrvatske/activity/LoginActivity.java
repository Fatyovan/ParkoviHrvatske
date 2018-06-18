package hr.parkovihrvatske.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.parkovihrvatske.R;
import hr.parkovihrvatske.adapter.ParkAdapter;
import hr.parkovihrvatske.model.User;
import hr.parkovihrvatske.persistence.Session;
import hr.parkovihrvatske.utility.Helper;
import hr.parkovihrvatske.utility.Utility;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_email)
    AppCompatEditText mEmail;
    @BindView(R.id.et_password)
    AppCompatEditText mPassword;
    @BindView(R.id.bt_login)
    AppCompatButton mLogin;
    @BindView(R.id.progressView)
    RelativeLayout mProgressView;
    @BindView(R.id.lyt_login_view)
    RelativeLayout mLoginView;
    @BindView(R.id.sp_park)
    AppCompatSpinner mPark;
    @BindView(R.id.rg_terminal)
    RadioGroup mTerminal;
    @BindView(R.id.lyt_terminal)
    RelativeLayout mTerminalLyt;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
        session = new Session(this);
        ParkAdapter adapter = new ParkAdapter(this, R.layout.park_item, Helper.PARKS);
        mPark.setAdapter(adapter);
        mPark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 9:
                        mTerminalLyt.setVisibility(View.VISIBLE);
                        break;
                    default:
                        mTerminalLyt.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getComponentsActions();
    }

    private void getComponentsActions() {
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLogin.setVisibility(!TextUtils.isEmpty(s.toString()) ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mLogin.setVisibility(View.VISIBLE);
            }
        });
        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    Utility.hideSoftKeyboard(LoginActivity.this, mPassword);
                    login();
                }
                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
        }
    }

    private void login() {
        boolean cancel = false;
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            cancel = true;
            mEmail.setError("This is field is required!");
        } else if (!Utility.isValidEmail(email)) {
            cancel = true;
            mEmail.setError("Invalid email format!");
        }
        if (TextUtils.isEmpty(password)) {
            cancel = true;
            mPassword.setError("This field is required!");
        }
        if (!cancel) {
            showProgress(true);
            User user = new User(email, password);
            session.setLogin(true, user);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            showProgress(false);
        }
    }

    private void showProgress(boolean show) {
        if (show) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
