package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proftaak1_4.ReadWriteData.SavedData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;


public class AntiSpellActivity extends AppCompatActivity {

    final static String TAG = "AntiSpellActivity";

    MqttAndroidClient client;
    SavedData data = SavedData.INSTANCE;

    private TextInputEditText test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_spell);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        this.test = (TextInputEditText) findViewById(R.id.inputAntiSpellCode);
        client = null;

        ConnectToChannel();
//        SubscribeToCobra();

        final EditText edittext = (EditText) findViewById(R.id.inputAntiSpellCode);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    System.out.println("yeet");
                    return true;
                }
                return false;
            }
        });

        final Button invoerButton = findViewById(R.id.invoerenButtonAntispreuk);
        invoerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("in ge-yeet");
                if(test != null && !test.getText().toString().isEmpty()){
                    int input = Integer.parseInt(test.getText().toString());
                    System.out.println(input);
                    System.out.println("there are no rules");
                }

            }
        });

        final Button regelsButton = findViewById(R.id.spelregelsButtonAntispreuk);
        regelsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switchTo(item.getItemId());
                    return true;
                }
            };


    private void switchTo(int navigation){

        Intent intent = null;
        switch(navigation){
            case R.id.attractions:
                intent = new Intent(this, AllAtractionActivity.class);
                break;

            case R.id.map:
                intent = new Intent(this, MainActivity.class);
                break;
        }

        if(intent != null)
            startActivity(intent);
    }


    /**
     * this is everything for the MQTT
     */

    private void ConnectToChannel() {

        String clientID = MqttClient.generateClientId();

        client = new MqttAndroidClient(this.getApplicationContext(), "maxwell.bps-software.nl:1883", clientID);

        try {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("androidTI");
            options.setPassword("&FN+g$$Qhm7j".toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(false);

            IMqttToken token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "onSuccess: CONNECTED!!!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "onFailure: COULD NOT CONNECT!!, exception: " + exception.getMessage());
                }
            });


        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void SubscribeToCobra(){
        final String top = "Student/A5/Games/CobraSpel";
        int qos = 2;

        try{
                IMqttToken subToken = client.subscribe(top, qos);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.d(TAG, "onSuccess: CONNECTED TO THE COBRA GAME!!!");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.d(TAG, "onFailure: COULD NOT CONNECT TO THE COBRA!, exception:  " + exception.getMessage());
                    }
                });


            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(TAG, "connectionLost: DISCONNECTED!");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d(TAG, "messageArrived: MESSAGE ARRIVED!!!");
                    data.getSessionData().getTopicMsg().put(topic, message.toString());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(TAG, "deliveryComplete: MESSAGE ARRIVED!!!");
                }
            });


        } catch (MqttSecurityException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
