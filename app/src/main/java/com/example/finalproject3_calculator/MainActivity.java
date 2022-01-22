package com.example.finalproject3_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView value,hasil;
    String isi="",tempFormula,formula;
    int val;
    AppCompatButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    AppCompatButton btn_add,btn_min,btn_del,btn_clear,btn_times,btn_div,btn_dot,btn_equal,btn_mod,btn_c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = findViewById(R.id.et_value);
        hasil = findViewById(R.id.et_hasil);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn_c = findViewById(R.id.btn_c);
        btn_add = findViewById(R.id.btn_add);
        btn_min = findViewById(R.id.btn_min);
        btn_del = findViewById(R.id.btn_del);
        btn_clear = findViewById(R.id.btn_clear);
        btn_times = findViewById(R.id.btn_times);
        btn_div = findViewById(R.id.btn_div);
        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);
        btn_mod = findViewById(R.id.btn_mod);

        btn_clear.setOnClickListener(view -> {
            hasil.setText("");
            value.setText("0");
            isi="";
        });
        btn_c.setOnClickListener(view -> {
            value.setText("0");
            isi="";
        });

        btn0.setOnClickListener(view -> updateDisplay("0"));
        btn1.setOnClickListener(view -> updateDisplay("1"));
        btn2.setOnClickListener(view -> updateDisplay("2"));
        btn3.setOnClickListener(view -> updateDisplay("3"));
        btn4.setOnClickListener(view -> updateDisplay("4"));
        btn5.setOnClickListener(view -> updateDisplay("5"));
        btn6.setOnClickListener(view -> updateDisplay("6"));
        btn7.setOnClickListener(view -> updateDisplay("7"));
        btn8.setOnClickListener(view -> updateDisplay("8"));
        btn9.setOnClickListener(view -> updateDisplay("9"));

        btn_dot.setOnClickListener(view -> updateDisplay("."));
        btn_add.setOnClickListener(view -> updateDisplay("+"));
        btn_min.setOnClickListener(view -> updateDisplay("-"));
        btn_div.setOnClickListener(view -> updateDisplay("/"));
        btn_times.setOnClickListener(view -> updateDisplay("x"));
        btn_mod.setOnClickListener(view -> updateDisplay("%"));

        btn_equal.setOnClickListener(view -> {
                Double result = null;

                isi = isi.replaceAll("x","*");
                isi = isi.replaceAll("%","/100");

                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                try {
                    result = (double) engine.eval(isi);
                }catch (ScriptException e){
                    Toast.makeText(MainActivity.this,"Invalid Input",Toast.LENGTH_SHORT).show();
                }
                if(result!=null){
                    hasil.setText(String.valueOf(result.doubleValue()));
                }
            }
        );

        btn_del.setOnClickListener(view -> {
            if (isi.equals("")) {
                value.setText("0");
            } else {
                int len = value.getText().length();
                String s = value.getText().toString();
                if (s.charAt(len - 1) == '.') {
                    value.setText(value.getText().subSequence(0, value.getText().length() - 1));
                    isi = value.getText().toString();
                } else {
                    value.setText(value.getText().subSequence(0, value.getText().length() - 1));
                    isi = value.getText().toString();
                }
            }
        });
    }

    private void updateDisplay(String givenValue){
        isi = isi+givenValue;
        value.setText(isi);
    }
}