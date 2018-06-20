package com.ztgm.iot;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublishSample {

	public static void main(String[] args) {

		String topic = "x";
		String content = "Message from MqttPublishSamp";
		int qos = 2;
		
			
			String broker = "tcp://192.168.100.239:18084";
			
	//String broker = "tcp://192.168.100.204:1883";
		String clientId = "JavaSample";
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setAutomaticReconnect(true);
			connOpts.setUserName("1");
			connOpts.setPassword("1".toCharArray());
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");

			new Thread(new Runnable() {
				int i = 0;

				public void run() {
					while (true) {
						String c = content + i;
						MqttMessage message = new MqttMessage(c.getBytes());
						message.setQos(qos);
						try {
							sampleClient.publish(topic, message);
							System.out.println("Publishing message: " + c);
							i++;
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}).start();

			System.out.println("Message published");
			// sampleClient.disconnect();
			// System.out.println("Disconnected");
			// System.exit(0);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}
