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

    private static final String debugTag = "GetPlayerAdapter";
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
    public static class ViewHolder
    {

        private TextView namePlayer,matricula,status;
        private Button searchButton;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
       ViewHolder  holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate (R.layout.player_row_layout, parent, false);
            holder = new ViewHolder();
            holder.namePlayer = (TextView) convertView.findViewById(R.id.playernameTV);
            holder.matricula = (TextView) convertView.findViewById(R.id.playernumberTV);
            holder.status= (TextView) convertView.findViewById(R.id.playerstatusTV);

            holder.searchButton.setTag(holder);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(this);

        Player player = listofplayers.get(pos);
        holder.namePlayer.setText(player.getName());
        holder.matricula.setText(player.getMatricula());
        holder.status.setText(player.getStatus());
        holder.searchButton.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {

        if (v instanceof Button) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            this.activity.startActivity(intent);

        }
        Log.d(debugTag, "OnClick pressed.");

    }
}

