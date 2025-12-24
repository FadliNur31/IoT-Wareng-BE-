package com.example.demo.config;

import com.example.demo.helper.SSLUtils;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MqttConfig {

    @Bean
    public IMqttClient mqttClient() throws Exception {

        String broker = "ssl://m780bced.ala.asia-southeast1.emqxsl.com:8883";
        String clientId = MqttClient.generateClientId();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("admin123".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);

        options.setSocketFactory(
                SSLUtils.getSocketFactory("emqxsl-ca.crt")
        );

        IMqttClient client = new MqttClient(broker, clientId);
        client.connect(options);

        return client;
    }
}
