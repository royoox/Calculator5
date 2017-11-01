package com.example.rasel.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private static double value1,value2,result; private boolean rplc = true;
    private String sign=""; private SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText1);

        value2 = 0; value1 = 0; rplc = false;
        sharedPreferences=getSharedPreferences("historyData",MODE_PRIVATE);
    }
    public void calculate(View view) {

        Button tmp = (Button) view;
        if(tmp.getId()==R.id.buttonplus){
            value1 = Double.parseDouble(text.getText().toString());
            sign = "+";
            text.setText("");
        }
        else if(tmp.getId()==R.id.buttonminus){
            value1 = Double.parseDouble(text.getText().toString());
            sign = "-";
            text.setText("");
        }
        else if(tmp.getId()==R.id.buttonmul){
            value1 = Double.parseDouble(text.getText().toString());
            sign = "*";
            text.setText("");
        }
        else if(tmp.getId()==R.id.buttondiv){
            value1 = Double.parseDouble(text.getText().toString());
            sign = "/";
            text.setText("");
        }
        else if(tmp.getId()==R.id.button1eual){
            value2 = Double.parseDouble(text.getText().toString());
            if(sign.equals("+")){
                result = value1 + value2;
            }
            else if(sign.equals("-")){
                result = value1 - value2;
            }
            else if(sign.equals("*")){
                result = value1 * value2;
            }
            else if(sign.equals("/")){
                result = value1 / value2;
            }
            else {
                Toast.makeText(this,"Please Input Properly",Toast.LENGTH_LONG).show();
            }
            rplc = true;
            text.setText(String.valueOf(result));

            String savedHistory = String.valueOf(value1)+sign+String.valueOf(value2)+"="+result+"\n";
            String newValue = sharedPreferences.getString(getString(R.string.history),"");

            savedHistory = savedHistory.concat(newValue);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.history),savedHistory);
            editor.apply();
        }
    }

    public void valueCliked(View view) {
        Button tmp = (Button) view;
        if(rplc) {
            text.setText(""); rplc =false;
        }
        text.append(tmp.getText().toString());
    }

    public void showHistory(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}

