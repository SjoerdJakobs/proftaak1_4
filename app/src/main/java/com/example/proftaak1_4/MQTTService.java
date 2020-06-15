package com.example.proftaak1_4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proftaak1_4.ReadWriteData.SavedData;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTService extends Service {

    final static String TAG = "MQTTService";

    private MqttAndroidClient client;
    private SavedData data = SavedData.INSTANCE;

    private final String topic = "Student/A5/Games/#";
    private IMqttToken token;

    @Override
    public void onDestroy() {
        client.unregisterResources();
        client.close();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final String clientID = MqttClient.generateClientId();
        client = new MqttAndroidClient(getApplicationContext(), "tcp://maxwell.bps-software.nl:1883", clientID);

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.d(TAG, "MQTT client lost connection to broker");

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d(TAG, "MQTT client received message " + message + " on topic " + topic);
                // Check what topic the message is for and handle accordingly
                data.getSessionData().getTopicMsg().put(topic, message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(TAG, "MQTT client delivery complete");
            }
        });
        connectBroker();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void subscribeTopic(){
        int qosqos = 0;
        try {
            // Try to subscribe to the topic
            this.token = client.subscribe(topic, qosqos);
            // Set up callbacks to handle the result
            this.token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "MQTT client is now subscribed to topic " + topic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "MQTT failed to subscribe to topic " + topic + " because: " + exception.getLocalizedMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    private void connectBroker(){
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
                    Log.d(TAG, "Connected");
                    System.out.println("CONNECTED TO BROKER");
                    subscribeTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //Log.d(TAG, "onFailure");
                    System.out.println("DIDNT CONNECT TO BROKER");
                    Log.e(TAG, "NOT Connected");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(TAG, "MQTT CONNECTION EXEP");
        }
    }

    private void disconnectBroker(){
        try {
            // Try to disconnect from the MQTT broker
            IMqttToken token = client.disconnect();
            // Set up callbacks to handle the result
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "MQTT client is now disconnected from MQTT broker");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "MQTT failed to disconnect from MQTT broker: " + exception.getLocalizedMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
