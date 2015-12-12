package com.example.ricardo.my_final_proyect.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ricardo.my_final_proyect.R;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//public class SettingActivity extends DialogFragment {
////ArrayList<String> list = new ArrayList<>();
////    @Override
////    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        final String[] items = getResources().getStringArray(R.array.setting);
////        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////        builder.setTitle("Settings").setMultiChoiceItems(R.array.setting, null, new DialogInterface.OnMultiChoiceClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
////                if(isChecked){
////                    list.add(items[which]);
////                }else if(list.contains(items[which])){
////                    list.remove(items[which]);
////                }
////            }
////        }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                String selections ="";
////                for(String ms : list){
////                    selections = selections +"\n"+ms;
////                }
////                Toast.makeText(getActivity(),"Setting guardadas: "+selections,Toast.LENGTH_SHORT).show();
////            }
////        });
////
////
////        return super.onCreateDialog(savedInstanceState);
////    }
//}
public class SettingActivity extends Activity implements View.OnClickListener{
    String FILENAME = "myFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setting);
        CheckBox sound = (CheckBox) findViewById(R.id.checkBox);
        CheckBox board = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox card = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox font = (CheckBox) findViewById(R.id.checkBox4);

        sound.setOnClickListener(this);
        board.setOnClickListener(this);
        card.setOnClickListener(this);
        font.setOnClickListener(this);
//

    }

    @Override
    public void onClick(View v) {

        int i=0;
        switch(v.getId()){
            case R.id.checkBox:


                Toast.makeText(this,"Sound Activated",Toast.LENGTH_SHORT).show();

                break;
            case R.id.checkBox2:

                Toast.makeText(this,"Board Activate",Toast.LENGTH_SHORT).show();

                break;
            case R.id.checkBox3:

                Toast.makeText(this,"Card Activate ",Toast.LENGTH_SHORT).show();

                break;
            case R.id.checkBox4:

                Toast.makeText(this,"Font Activate ",Toast.LENGTH_SHORT).show();

                break;


        }

    }

}