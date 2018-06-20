package com.ztgm.iot;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscribeSample {

    public static void main(String[] args) {

        String topic        = "1/1111";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
    	String broker = "tcp://192.168.100.239:18084";
        String clientId     = "JavaSample2";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            
           
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
        	connOpts.setAutomaticReconnect(true);
            connOpts.setUserName("0199dad7ddc844729b78faadbf5e9318");
            connOpts.setPassword("cf2e99c1213c4836b5f4c4ae06868772".toCharArray());
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            
            sampleClient.subscribe(topic, new IMqttMessageListener(){

				@Override
				public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
					// TODO Auto-generated method stub
					 try {     
	                        System.out.println("arrived :"+arg1.toString());    
	                    } catch (Exception e) {     
	                        e.printStackTrace();     
	                    }  
				}
            	
            });
     
                       
         //   System.out.println("Message published");
//            sampleClient.disconnect();
//            System.out.println("Disconnected");
//            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
