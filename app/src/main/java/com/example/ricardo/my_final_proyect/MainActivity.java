package com.example.ricardo.my_final_proyect;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.ricardo.my_final_proyect.Dialogs.SettingActivity;
import com.example.ricardo.my_final_proyect.Player.GetPlayersActivity;
//import com.example.ricardo.my_final_proyect.Dialogs.SettingActivity;
import com.example.ricardo.my_final_proyect.Dialogs.RegisterActivity;
import com.example.ricardo.my_final_proyect.Game.GameActivity;
import com.example.ricardo.my_final_proyect.Game.ScoreActivity;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int CAMERA_REQUEST = 0;
    ImageView img;
    private String drawerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView) findViewById(R.id.circle_image);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.take_picture:


                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAMERA_REQUEST);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void openScore(View view) {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (data != null) {
                String message = data.getStringExtra("MESSAGE");
                TextView TView = (TextView) findViewById(R.id.printResult);
                TView.setText("Best Record by: " + message);
            }
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Dialog dialog = new Dialog(MainActivity.this);
        int id = item.getItemId();


        if (id == R.id.action_settings) {
//           SettingActivity settingActivity = new SettingActivity();
//            settingActivity.show(getFragmentManager(), "");
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);

            return true;
        }
        if(id==R.id.invite_friend){
          Intent intent = new Intent(this,GetPlayersActivity.class);
            startActivity(intent);

        }
        if (id == R.id.action_sign_in) {
            RegisterActivity register = new RegisterActivity();
            register.show(getFragmentManager(), "");
        }
        if (id == R.id.action_about) {
            dialog.setTitle("About");
            dialog.setContentView(R.layout.dialog_about);
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}