package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;


public class AntiSpellActivity extends AppCompatActivity {

    private Button invoerButton;
    private TextInputLayout codeInput;

    private TextView textView;

    private static final String LOGTAG = MainActivity.class.getName();

    private String tokenCobra;
    private String tokenMemory;

    private MqttAndroidClient client;

    @Override
    protected void onDestroy() {
        disconnectFromBroker(client);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_spell);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        invoerButton = findViewById(R.id.button3);
        codeInput = findViewById(R.id.textInputLayout2);
        textView = findViewById(R.id.textView3);

        final String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient( getApplicationContext(), "tcp://maxwell.bps-software.nl:1883", clientId);



        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.d(LOGTAG, "MQTT client lost connection to broker");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d(LOGTAG, "MQTT client received message " + message + " on topic " + topic);
                // Check what topic the message is for and handle accordingly

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(LOGTAG, "MQTT client delivery complete");
            }
        });

        connectToBroker(client, clientId);



//        invoerButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                final String topic = "Student/A5/Games/#";
//                final int qos = 0;
//
//                try {
//                    IMqttToken subToken = client.subscribe(topic, qos);
//                    subToken.setActionCallback(new IMqttActionListener() {
//                        @Override
//                        public void onSuccess(IMqttToken asyncActionToken) {
//                            // The message was published
//                            System.out.println("SUBSCRIBED TO TOPIC");
//                        }
//
//                        @Override
//                        public void onFailure(IMqttToken asyncActionToken,
//                                              Throwable exception) {
//                            // The subscription could not be performed, maybe the user was not
//                            // authorized to subscribe on the specified topic e.g. using wildcards
//                            System.out.println("COULDNT SUBSCRIBE TO TOPIC");
//                        }
//                    });
//
//
//                } catch (MqttException e) {
//                    e.printStackTrace();
//                }
//
//                client.setCallback(new MqttCallback() {
//                    @Override
//                    public void connectionLost(Throwable cause) {
//
//                    }
//
//                    @Override
//                    public void messageArrived(String topic, MqttMessage message) throws Exception {
//                        if ( message.toString().equals(codeInput.getEditText())){
//                            textView.setText("HOCUS");
//                        }
//                        System.out.println("FETCHED MESSAGE IN TOPIC");
//                    }
//
//                    @Override
//                    public void deliveryComplete(IMqttDeliveryToken token) {
//
//                    }
//                });
//            }
//        });
    }

    private void connectToBroker(MqttAndroidClient client, String clientId){
        MqttConnectOptions options = new MqttConnectOptions();

        options.setUserName("androidTI");
        options.setPassword("&FN+g$$Qhm7j".toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    //Log.d(TAG, "onSuccess");
                    Log.d(LOGTAG, "Connected");
                    System.out.println("CONNECTED TO BROKER");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //Log.d(TAG, "onFailure");
                    System.out.println("DIDNT CONNECT TO BROKER");
                    Log.e(LOGTAG, "NOT Connected");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(LOGTAG, "MQTT CONNECTION EXEP");
        }
    }

    private void disconnectFromBroker(MqttAndroidClient client) {
        try {
            // Try to disconnect from the MQTT broker
            IMqttToken token = client.disconnect();
            // Set up callbacks to handle the result
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(LOGTAG, "MQTT client is now disconnected from MQTT broker");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(LOGTAG, "MQTT failed to disconnect from MQTT broker: " + exception.getLocalizedMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
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
}
