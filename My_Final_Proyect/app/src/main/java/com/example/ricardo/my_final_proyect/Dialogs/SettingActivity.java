package com.example.ricardo.my_final_proyect.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.ricardo.my_final_proyect.R;

import java.util.TooManyListenersException;


public class SettingActivity extends DialogFragment implements CompoundButton.OnCheckedChangeListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        ToggleButton sound = (ToggleButton)getActivity().findViewById(R.id.toggleButton);
//        sound.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) getActivity());
        builder.setView(inflater.inflate(R.layout.dialog_setting, null))
                // Add action buttons
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        saveSetting();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();

                    }
                });
        return builder.create();
    }

    private void saveSetting() {
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
if(isChecked){
    Toast.makeText(getActivity(), "Sound is on", Toast.LENGTH_SHORT).show();
}else{
    Toast.makeText(getActivity(), "Sound is off", Toast.LENGTH_SHORT).show();
}

    }
}




/*
    public void writeToFile(String data) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            readFromFile();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = getActivity().openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Log.i("",ret);
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}*/
