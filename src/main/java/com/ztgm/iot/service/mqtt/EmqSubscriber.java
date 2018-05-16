package com.ztgm.iot.service.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

//@Service
//@PropertySource("classpath:project.properties")
public class EmqSubscriber {
/*
    @Value("${emqtt_broker}")
    private String broker;
    public void receive() {
        String topic = "MQTT/+";
        String content = "Message from MqttPublishSample";
        int qos = 2;
        String broker = "tcp://192.168.100.187:1883";
        String clientId = "Receiver1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            sampleClient.subscribe(topic, new IMqttMessageListener() {

                @Override
                public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
                    // TODO Auto-generated method stub
                    try {
                        System.out.println(" Receiver1  receive message length : " + arg1.toString().length());
                        System.out.println("  message :" + arg1.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });


            //        sampleClient.disconnect();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
    */
}
