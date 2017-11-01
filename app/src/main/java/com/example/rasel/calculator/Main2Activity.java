package com.example.rasel.calculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {
    private TextView textView, showFileOutput;
    private SharedPreferences preferences;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView) findViewById(R.id.textView);
        showFileOutput = (TextView) findViewById(R.id.showFileOutput);
        preferences = getSharedPreferences("historyData", MODE_PRIVATE);
        filename = "filename.txt";

    }

    @Override
    protected void onResume() {
        String newValue = preferences.getString(getString(R.string.history), "");
        textView.setText(newValue);
        super.onResume();
    }

    public void createFile(View view) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(textView.getText().toString().getBytes());
            File ops = getFilesDir();
            Toast.makeText(getApplicationContext(), ops.toString(), Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void ShowFile(View view) {
        FileInputStream inputStream = null;
        try {
            int tmp = -1;
            inputStream = openFileInput(filename);
            StringBuilder stringBuilder = new StringBuilder();
            while ((tmp = inputStream.read()) != -1) {
                stringBuilder.append((char) inputStream.read());
            }
            showFileOutput.setText(stringBuilder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

