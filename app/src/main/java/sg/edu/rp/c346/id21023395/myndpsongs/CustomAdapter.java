package sg.edu.rp.c346.id21023395.myndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Song>objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvSongTitle);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        TextView tvStar = rowView.findViewById(R.id.tvStars);
        TextView tvSingers = rowView.findViewById(R.id.tvSingers);
        TextView tvRating = rowView.findViewById(R.id.tvRating);

        // Obtain the Android Version information based on the position
        Song current = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(current.getTitle());
        tvYear.setText(String.valueOf(current.getYear()));
        tvSingers.setText(current.getSingers());
        tvStar.setText(current.toString());
        tvRating.setText("(" + current.getStars() + ")");
        return rowView;
    }
}
