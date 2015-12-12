package com.example.ricardo.my_final_proyect.Adapter;
        import java.util.ArrayList;

        import android.content.Intent;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.View.OnClickListener;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.TextView;

        import com.example.ricardo.my_final_proyect.Player.GetPlayersActivity;
        import com.example.ricardo.my_final_proyect.Player.Player;
        import com.example.ricardo.my_final_proyect.R;


public class GetPlayerAdapter extends BaseAdapter implements OnClickListener {

    private static final String debugTag = "GetPlayerAdapter";
    private GetPlayersActivity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<Player> playerArrayList;


    public GetPlayerAdapter(GetPlayersActivity a, LayoutInflater l, ArrayList<Player> data) {
        this.activity = a;
        this.layoutInflater = l;
        this.playerArrayList = data;
    }

    @Override
    public int getCount() {
        return this.playerArrayList.size();
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
        Player player;
    }

    @Override
    public View getView( int pos, View convertView, ViewGroup parent) {

         ViewHolder  holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate (R.layout.player_row_layout, parent, false);
            holder.namePlayer = (TextView) convertView.findViewById(R.id.playernameTV);
            holder.matricula = (TextView) convertView.findViewById(R.id.playernumberTV);
            holder.status= (TextView) convertView.findViewById(R.id.playerstatusTV);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }



        Player player = playerArrayList.get(pos);
        holder.namePlayer.setText(player.getName());
        holder.matricula.setText(player.getMatricula());
        holder.status.setText(player.getStatus());

        return convertView;
    }

    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        if (v instanceof Button) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            this.activity.startActivity(intent);

        }
        Log.d(debugTag, "OnClick pressed.");

    }
}

