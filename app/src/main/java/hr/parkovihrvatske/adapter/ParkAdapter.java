package hr.parkovihrvatske.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import hr.parkovihrvatske.R;
import hr.parkovihrvatske.view.ParkViewHolder;

/**
 * Created by Damjan Miloševski on 13.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class ParkAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> names;
    private LayoutInflater mInflater;

    public ParkAdapter(@NonNull Context context, int resource, List<String> names) {
        super(context, R.layout.park_item, resource, names);
        this.context = context;
        this.names = names;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        ParkViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.park_item, parent, false);
        }
        holder = new ParkViewHolder();
        holder.name = convertView.findViewById(R.id.tv_park_value);
        holder.image = convertView.findViewById(R.id.ic_park_icon);
        holder.name.setText(names.get(position));
        holder.image.setImageDrawable(context.getDrawable(R.drawable.ic_croatia));

        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ParkViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.park_item, parent, false);
        }
        holder = new ParkViewHolder();
        holder.name = convertView.findViewById(R.id.tv_park_value);
        holder.image = convertView.findViewById(R.id.ic_park_icon);
        holder.name.setText(names.get(position));
        holder.image.setImageDrawable(context.getDrawable(R.drawable.ic_croatia));

        return convertView;
    }

}
