package com.example.ricardo.my_final_proyect.Player;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;

import com.example.ricardo.my_final_proyect.Adapter.GetPlayerAdapter;

import com.example.ricardo.my_final_proyect.R;

import java.util.ArrayList;

public class GetPlayersActivity extends Activity {
    private ArrayList<Player> playerArrayList;
    private ListView playerList;
    private LayoutInflater layout_inflator;
    private Button searchButton;
    private InputMethodManager inMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_getplayers);
        this.inMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.playerList = (ListView) findViewById(R.id.playerListView);
        this.layout_inflator = LayoutInflater.from(this);
        this.searchButton = (Button) this.findViewById(R.id.searchButton);
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                inMgr.hideSoftInputFromWindow(searchButton.getWindowToken(), 0);
                Service service = new Service(GetPlayersActivity.this);
                try {
                    service.execute();
                } catch (Exception e) {
                    service.cancel(true);
                    Log.w("GetPlayersActivity",getResources().getString(R.string.no_players));
                }
            }

        });

        // Restore any already fetched data on orientation change.
        final Object[] data = (Object[]) getLastNonConfigurationInstance();
        if(data != null) {
            this.playerArrayList = (ArrayList<Player>) data[0];

            playerList.setAdapter(new GetPlayerAdapter(this,this.layout_inflator, this.playerArrayList));
        }

    }






    @Override
    public Object onRetainNonConfigurationInstance() {
        Object[] myStuff = new Object[2];
        myStuff[0] = this.playerArrayList;

        return myStuff;
    }



    public void setPlayer(ArrayList<Player> playersArrayList) {
        this.playerArrayList = playersArrayList;
        this.playerList.setAdapter(new GetPlayerAdapter(this,this.layout_inflator, this.playerArrayList));
    }


    }




