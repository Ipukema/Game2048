package com.example.ricardo.my_final_proyect.Adapter;
        import java.util.ArrayList;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.View.OnClickListener;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.ricardo.my_final_proyect.MainActivity;
        import com.example.ricardo.my_final_proyect.Player.Player;
        import com.example.ricardo.my_final_proyect.R;


public class GetPlayerAdapter extends BaseAdapter implements OnClickListener {

    private static final String debugTag = "TrackDataAdapter";
    private MainActivity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<Player> listofplayers;


    public GetPlayerAdapter(MainActivity a, LayoutInflater l, ArrayList<Player> data) {
        this.activity = a;
        this.layoutInflater = l;
        this.listofplayers = data;
    }

    @Override
    public int getCount() {
        return this.listofplayers.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
      /*  MyViewHolder  holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate (R.layout.player_row_layout, parent, false);
            holder = new MyViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.playernameTV);
            holder.matricula = (TextView) convertView.findViewById(R.id.playernumberTV);
            holder.satuts= (TextView) convertView.findViewById(R.id.playerstatusTV);

            holder.trackButton.setTag(holder);
            convertView.setTag(holder);
        }
        else {
            holder = (MyViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(this);

        TrackData track = tracks.get(pos);
        holder.track = track;
        holder.trackName.setText(track.getName());
        holder.artistName.setText(track.getArtist());
        holder.trackButton.setOnClickListener(this);
        if(track.getImageUrl() != null) {
            holder.icon.setTag(track.getImageUrl());
            Drawable dr = imgFetcher.loadImage(this, holder.icon);
            if(dr != null) {
                holder.icon.setImageDrawable(dr);
            }
        } else {
            holder.icon.setImageResource(R.drawable.filler_icon);
        }
*/
        return convertView;
    }

    @Override
    public void onClick(View v) {
        /*MyViewHolder holder = (MyViewHolder) v.getTag();
        if (v instanceof Button) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(holder.track.getArtistUrl()));
            this.activity.startActivity(intent);

        } else if (v instanceof View) {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(holder.track.getTrackUrl()));
            this.activity.startActivity(intent);
        }
        Log.d(debugTag, "OnClick pressed.");*/

    }
}

