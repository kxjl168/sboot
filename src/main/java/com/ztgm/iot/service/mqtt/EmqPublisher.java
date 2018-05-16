package com.ztgm.iot.service.mqtt;

import com.ztgm.iot.util.TokenUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:project.properties")
public class EmqPublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${emqtt.broker}")
    private String broker;

    public void send(String topic ,String content){

        int qos             = 2;
//        String broker       = "tcp://192.168.101.127:1883";
        String clientId     = "userid:";
        if(TokenUtil.getCurrentUser()!=null){
            clientId+=TokenUtil.getCurrentUser().getId();
        }else{
            clientId+="System";
        }

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
//            connOpts.setUserName("zhang");
//		    connOpts.setPassword("123456".toCharArray());

            sampleClient.connect(connOpts);
//            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            message.setRetained(true);

            sampleClient.publish(topic, message);
            sampleClient.disconnect();

        } catch(MqttException me) {
            logger.info("reason "+me.getReasonCode());
            logger.info("msg "+me.getMessage());
            logger.info("loc "+me.getLocalizedMessage());
            logger.info("cause "+me.getCause());
            logger.info("excep "+me);
            me.printStackTrace();
        }
    }

    public void send(String topic ,String content,String userName,String password){

        int qos             = 2;
//        String broker       = "tcp://192.168.101.127:1883";
        String clientId     = "userid:";
        if(TokenUtil.getCurrentUser()!=null){
            clientId+=TokenUtil.getCurrentUser().getId();
        }else{
            clientId+="System";
        }

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(userName);
		    connOpts.setPassword(password.toCharArray());

            sampleClient.connect(connOpts);
//            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            message.setRetained(true);

            sampleClient.publish(topic, message);
            sampleClient.disconnect();

            logger.info("Topic: "+topic+", Content: "+content);

        } catch(MqttException me) {
            logger.info("reason "+me.getReasonCode());
            logger.info("msg "+me.getMessage());
            logger.info("loc "+me.getLocalizedMessage());
            logger.info("cause "+me.getCause());
            logger.info("excep "+me);
            me.printStackTrace();
        }
    }
}
