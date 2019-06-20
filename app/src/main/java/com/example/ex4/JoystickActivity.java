package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JoystickActivity extends AppCompatActivity {

    private Client client;

    private void func(final float x,final float y) {
        // Send message
        Thread thread = new Thread() {
            @Override
            public void run() {
                String aileron = "set /controls/flight/aileron "+ x +"\r\n";
                client.send(aileron);
                String elevator = "set /controls/flight/elevator " + y + "\r\n";
                client.send(elevator);
            }
        };
        thread.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String ip = intent.getStringExtra("ip");
        String port = intent.getStringExtra("port");
        //final Client client = new Client(ip, Integer.parseInt(port));
        client = new Client(ip, Integer.parseInt(port));
        Thread thread = new Thread() {
            @Override
            public void run() {
                client.Connect();
            }
        };

        thread.start();

        View view = new JoystickView(this, new JoystickView.MoveHandler() {
            @Override
            public void handle(float x, float y) {
                JoystickActivity.this.func(x,y);
            }
        });

        setContentView(view);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Thread thread = new Thread() {
            @Override
            public void run() {
                client.Disconnect();
            }
        };
        thread.start();

    }



}
