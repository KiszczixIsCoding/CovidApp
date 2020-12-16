package pl.emb.covidsupport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CountriesAdapter extends ArrayAdapter<CountryItem> {

    public CountriesAdapter(Context context, ArrayList<CountryItem> countriesList) {
        super(context, 0, countriesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView2(position, convertView, parent);
    }

    public View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate
                    (R.layout.spinner_layout, parent, false);
        }
        ImageView image = convertView.findViewById(R.id.flag);
        TextView textView = convertView.findViewById(R.id.country);

        CountryItem item = getItem(position);
        if (item != null) {
            image.setImageResource(item.getCountryId());
            textView.setText(item.getCountryName());
        }
        return convertView;
    }

    public View initView2(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate
                    (R.layout.item_layout, parent, false);
        }
        ImageView image = convertView.findViewById(R.id.flag);
        TextView textView = convertView.findViewById(R.id.country);

        CountryItem item = getItem(position);
        if (item != null) {
            image.setImageResource(item.getCountryId());
            textView.setText(item.getCountryName());
        }
        return convertView;
    }

}
