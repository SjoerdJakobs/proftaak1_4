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


import com.example.proftaak1_4.ReadWriteData.SavedData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;


import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;


public class AntiSpellActivity extends AppCompatActivity {


    final static String TAG = "AntiSpellActivity";

    MqttAndroidClient client;
    private SavedData data = SavedData.INSTANCE;



    private Button invoerButton;
    private TextInputEditText codeInput;

    private TextView textViewFirstWord;
    private TextView textViewSecondWord;

    private static final String LOGTAG = MainActivity.class.getName();

    private String tokenCobra;
    private String tokenMemory;


    private final int qos = 0;

    private final String topic = "Student/A5/Games/CobraSpel";


    private IMqttToken token;

    @Override
    protected void onDestroy() {

//        client.unregisterResources();
//        client.close();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_spell);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        MenuItem item = bottomNavigationView.getMenu().findItem(R.id.spell);
        item.setChecked(true);

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

//        final Button invoerButton = findViewById(R.id.invoerenButtonAntispreuk);
//        invoerButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Code here executes on main thread after user presses button
//                System.out.println("in ge-yeet");
//
//            }
//        });

        final Button regelsButton = findViewById(R.id.spelregelsButtonAntispreuk);
        final Intent intent = new Intent(this,StartActivity.class);
        regelsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if(intent != null)
                    startActivity(intent);
            }
        });





        invoerButton = findViewById(R.id.invoerenButtonAntispreuk);
        codeInput = findViewById(R.id.inputAntiSpellCode);
        textViewFirstWord = findViewById(R.id.textView3);
        textViewSecondWord = findViewById(R.id.textView4);

        //final String clientId = MqttClient.generateClientId();
        //client = new MqttAndroidClient(getApplicationContext(), "tcp://maxwell.bps-software.nl:1883", clientId);


//        client.setCallback(new MqttCallback() {
//            @Override
//            public void connectionLost(Throwable cause) {
//                Log.d(LOGTAG, "MQTT client lost connection to broker");
//
//            }
//
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception {
//                Log.d(LOGTAG, "MQTT client received message " + message + " on topic " + topic);
//                // Check what topic the message is for and handle accordingly
//                data.getSessionData().getTopicMsg().put(topic, message.toString());
//            }
//
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//                Log.d(LOGTAG, "MQTT client delivery complete");
//            }
//        });

        //connectToBroker(client, clientId);


        invoerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println(codeInput.getText().toString());

                for(String key : data.getSessionData().getTopicMsg().keySet()){
                    if(data.getSessionData().getTopicMsg().get(key).equals(codeInput.getText().toString())){

                        System.out.println(data.getSessionData().getTopicMsg().keySet());
                        if(key.contains("CobraSpel")){
                                for(AttractionInformation i : data.getSessionData().getAllAttractions() ){
                                    if(i.getTitle().equals("Cobra")){
                                        System.out.println("IK GA HET CHECKEN");
                                        textViewFirstWord.setText("HOCUS");
                                        i.setUnlocked(true);
                                    }
                                }
                        }
                        else if(key.contains("MemorySpel")){
                            for(AttractionInformation i : data.getSessionData().getAllAttractions() ){
                                if(i.getTitle().equals("Fabel Woud")){
                                    System.out.println("IK GA HET CHECKEN");
                                    textViewSecondWord.setText("POCUS");
                                    i.setUnlocked(true);
                                }
                            }
                        }

                        codeInput.setText("");
                        return;
                    }
                }

                textViewFirstWord.setText("FAILED");
                codeInput.setText("");
//                if (topicMsg.containsValue(codeInput.getText().toString())) {
//                    textView.setText("HOCUS");
//                } else {
//                    textView.setText("FAILED");
//                }
            }
        });
    }

    private void subscribeTopic() {
        String top = "Student/A5/Games/#";
        int qosqos = 0;
        try {
            // Try to subscribe to the topic
            this.token = client.subscribe(top, qosqos);
            // Set up callbacks to handle the result
            this.token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(LOGTAG, "MQTT client is now subscribed to topic " + topic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(LOGTAG, "MQTT failed to subscribe to topic " + topic + " because: " + exception.getLocalizedMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void connectToBroker(MqttAndroidClient client, String clientId) {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setConnectionTimeout(240000);
        options.setUserName("androidTI");
        options.setPassword("&FN+g$$Qhm7j".toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);

        try {
            this.token = client.connect(options);
            this.token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    //Log.d(TAG, "onSuccess");
                    Log.d(LOGTAG, "Connected");
                    System.out.println("CONNECTED TO BROKER");
                    subscribeTopic();
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

//    private void disconnectFromBroker(MqttAndroidClient client) {
//        try {
//            // Try to disconnect from the MQTT broker
//            IMqttToken token = client.disconnect();
//            // Set up callbacks to handle the result
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Log.d(LOGTAG, "MQTT client is now disconnected from MQTT broker");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Log.e(LOGTAG, "MQTT failed to disconnect from MQTT broker: " + exception.getLocalizedMessage());
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switchTo(item.getItemId());
                    return true;
                }
            };


    private void switchTo(int navigation) {

        Intent intent = null;
        switch (navigation) {
            case R.id.attractions:
                intent = new Intent(this, AllAtractionActivity.class);
                break;

            case R.id.map:
                intent = new Intent(this, MainActivity.class);
                break;
        }

        if (intent != null)
            startActivity(intent);
    }
    


}
