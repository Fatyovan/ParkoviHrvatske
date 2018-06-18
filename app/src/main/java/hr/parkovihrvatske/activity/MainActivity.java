package hr.parkovihrvatske.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.gson.Gson;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.parkovihrvatske.R;
import hr.parkovihrvatske.api.ApiService;
import hr.parkovihrvatske.model.ParkSeason;
import hr.parkovihrvatske.model.ParkSeasonResult;
import hr.parkovihrvatske.model.ParkSeasonsResult;
import hr.parkovihrvatske.model.ParkTicket;
import hr.parkovihrvatske.model.ParkTicketResult;
import hr.parkovihrvatske.network.RetrofitBuilder;
import hr.parkovihrvatske.persistence.AppDatabase;
import hr.parkovihrvatske.persistence.Session;
import hr.parkovihrvatske.utility.Helper;
import hr.parkovihrvatske.utility.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.bt_scan)
    AppCompatButton mScan;
    private ApiService apiService;
    @BindView(R.id.tv_season)
    AppCompatTextView mSeason;
    @BindView(R.id.tv_tickets)
    AppCompatTextView mTickets;
    @BindView(R.id.lyt_scan)
    RelativeLayout mLytScan;
    @BindView(R.id.lyt_ticket)
    RelativeLayout mLytTicket;
    @BindView(R.id.tv_season_ticket)
    AppCompatTextView mSeasonTicket;
    @BindView(R.id.tv_ticket_id)
    AppCompatTextView mTicketId;
    @BindView(R.id.tv_ticket_valid)
    AppCompatTextView mTicketValid;
    @BindView(R.id.tv_ticket_type)
    AppCompatTextView mTicketType;
    @BindView(R.id.tv_ticket_hash)
    AppCompatTextView mTicketHash;
    @BindView(R.id.bt_cancel)
    AppCompatButton mCancel;
    @BindView(R.id.bt_validate)
    AppCompatButton mValidate;
    private String seasonString;
    private Session session;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mScan.setOnClickListener(this);
        mValidate.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        session = new Session(this);
        apiService = RetrofitBuilder.createService(ApiService.class);
        // appDatabase = AppDatabase.getDatabase(this);
        Call<ParkSeasonResult> getParkSeason = apiService.getParkSeason("2b00d0e1-4b7f-440b-ac58-8ead9d307e64");
        getParkSeason.enqueue(new Callback<ParkSeasonResult>() {
            @Override
            public void onResponse(@NonNull Call<ParkSeasonResult> call, @NonNull Response<ParkSeasonResult> response) {
                if (response.isSuccessful()) {
                    ParkSeasonResult result = response.body();
                    if (result != null && result.getStatus() == 200) {
                        ParkSeason season = result.getParkSeason();
                        if (season != null) {
                            seasonString = Utility.displayDate(Utility.getDateFromString(season.getDateFrom()), "hr") + " - " +
                                    Utility.displayDate(Utility.getDateFromString(season.getDateTo()), "hr");
                            mSeason.setText(seasonString);
                            mTickets.setText("Tickets available: " + season.getSeasonQuantity());
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParkSeasonResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //shuffleBackground();
    }

    private void shuffleBackground() {
        if (session.isSlideShowEnabled()) {
            final Random random = new Random();
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    mLytScan.setBackgroundResource(Helper.PARKS_OF_CROATIA[
                            random.nextInt(Helper.PARKS_OF_CROATIA.length)]);
                    mLytTicket.setBackgroundResource(Helper.PARKS_OF_CROATIA[
                            random.nextInt(Helper.PARKS_OF_CROATIA.length)]);
                    handler.postDelayed(this, session.getTimeout());
                }
            };
            handler.postDelayed(runnable, 0);
        } else {
            mLytTicket.setBackgroundColor(Color.parseColor("#d3d3d3"));
            mLytScan.setBackgroundColor(Color.parseColor("#d3d3d3"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Log.d(TAG, "COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
        if (requestCode == Helper.REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(TAG, "Have scan result in your app activity :" + result);
            displayTicketInfo(result, true);
        }
    }

    private void displayTicketInfo(String result, boolean show) {
        mLytScan.setVisibility(show ? View.GONE : View.VISIBLE);
        mLytTicket.setVisibility(show ? View.VISIBLE : View.GONE);
        if (result != null) {
//            Gson gson = new Gson();
//            ParkTicketResult ticketResult = gson.fromJson(result, ParkTicketResult.class);
//            ParkTicket ticket = ticketResult.getTicket();
//            mSeasonTicket.setText(seasonString);
//            mTicketId.setText(ticket.getTicketId().toString());
//            String valid = Utility.displayDate(Utility.getDateFromString(ticket.getDateFrom()), "hr") + " - " +
//                    Utility.displayDate(Utility.getDateFromString(ticket.getDateTo()), "hr");
//            mTicketValid.setText(valid);
//            mTicketType.setText(ticket.getOperatorType().name());
            mTicketHash.setText(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_logout:
                session.clearSession();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scan:
                openScanner(Helper.REQUEST_CODE_QR_SCAN);
                break;
            case R.id.bt_validate:
                displayTicketInfo(null, false);
                break;
            case R.id.bt_cancel:
                displayTicketInfo(null, false);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Helper.PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openScanner(Helper.REQUEST_CODE_QR_SCAN);
                } else {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA);
                    if (!showRationale) {
                        Toast.makeText(MainActivity.this, "This app cannot be used without camera permission!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setCancelable(false);
                        alertDialog.setTitle("Camera permission");
                        alertDialog.setMessage("Camera is required so that you can scan QR code and then send the result back to the app for further processing. In case you don't allow this app to use camera permission, you'll not be able to use it unless you manually give her permissions in Settings->Apps");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(MainActivity.this,
                                                new String[]{Manifest.permission.CAMERA}, Helper.PERMISSION_CAMERA);
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void openScanner(int requestQRCodeScan) {
        int permissionCamera = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
//        int permissionWriteExternalStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int permissionReadExternalStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
//        permissionWriteExternalStorage != PackageManager.PERMISSION_GRANTED &&
//                permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                Helper.PERMISSION_WRITE_EXTERNAL_STORAGE);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                Helper.PERMISSION_READ_EXTERNAL_STORAGE);
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Helper.PERMISSION_CAMERA);
        } else {
            startActivityForResult(new Intent(MainActivity.this, QrCodeActivity.class),
                    requestQRCodeScan);
        }
    }
}
