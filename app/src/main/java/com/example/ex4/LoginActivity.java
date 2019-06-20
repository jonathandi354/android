package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Connect(View view)
    {
        EditText ip_string = (EditText)findViewById(R.id.ip);
        EditText port_string = (EditText)findViewById(R.id.port);
        Intent intent = new Intent(this, JoystickActivity.class);
        String ip = ip_string.getText().toString();
        String port = port_string.getText().toString();
        intent.putExtra("ip",ip);
        intent.putExtra("port", port);
        startActivity(intent);



    }
}
