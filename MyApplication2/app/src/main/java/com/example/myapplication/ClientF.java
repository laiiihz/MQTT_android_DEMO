package com.example.myapplication;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Calendar;
import java.util.Random;

public class ClientF {
    private static final String SERVER_URL = "tcp://laihz.tech:1883";
    //private static final String TOPIC = "$SYS/broker/clients/connected";
    private static final String TOPIC = "Test";



    //private ScheduledExecutorService scheduler;
    private String RandomIDGen(){
        int seed= Calendar.getInstance().get(Calendar.MILLISECOND);
        Random random=new Random(seed);
        String result="ID-"+random.nextInt(100000);
        MainActivity.MyID=result;
        return result;
    }

    void start() throws Exception{
        MqttClient client = new MqttClient(SERVER_URL, this.RandomIDGen(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(20);
        PushCallBackClient pushCallBackClient=new PushCallBackClient();
        client.setCallback(pushCallBackClient);


        //MqttTopic topic = client.getTopic(TOPIC);
        client.connect(options);
        int[] Qos = {1};
        String[] topic1 = {TOPIC};
        client.subscribe(topic1,Qos);
    }


    /*********** FOR CONSOLE TEST ONLY**************/
    public  static void main (String[] args) throws Exception{
        ClientF client = new ClientF();
        client.start();
    }
}