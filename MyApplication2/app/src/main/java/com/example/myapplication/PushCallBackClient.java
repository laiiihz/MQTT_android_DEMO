package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class PushCallBackClient implements MqttCallback {
    @Override
    //处理链接断开
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println(throwable.getMessage());
        System.out.println("连接断开，可以做重连");
    }

    @Override
    //处理消息送达
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String msg = new String(mqttMessage.getPayload());
        System.out.println("接收消息主题 : " + s);
        System.out.println("接收消息Qos : " + mqttMessage.getQos());
        System.out.println("接收消息内容 : " + msg);
        MainActivity.MsgTime++;
        MainActivity.MsgContext=msg;
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}