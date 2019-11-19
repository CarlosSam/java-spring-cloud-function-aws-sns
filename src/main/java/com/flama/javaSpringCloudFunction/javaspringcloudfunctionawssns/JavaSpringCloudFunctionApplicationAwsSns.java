package com.flama.javaSpringCloudFunction.javaspringcloudfunctionawssns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
@ConfigurationPropertiesScan({ "com.flama.javaSpringCloudFunction.config" })
public class JavaSpringCloudFunctionApplicationAwsSns {

	private static final Logger log = LoggerFactory.getLogger(JavaSpringCloudFunctionApplicationAwsSns.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringCloudFunctionApplicationAwsSns.class, args);
	}

	@Bean
	public NotificationMessagingTemplate notificationMessagingTemplate(){
		AmazonSNS amazonSNS = AmazonSNSClientBuilder.defaultClient();
		return new NotificationMessagingTemplate(amazonSNS);
	}

	@Autowired
	private NotificationMessagingTemplate notificationMessagingTemplate;

	@Bean
	public Function<Map, Map> hello(){
		return input -> {
			log.info("hello with input: {}", input);
			Map output = new HashMap();
			output.put("greeting", "Hello " + input.get("name") + " from a map");
			this.notificationMessagingTemplate.sendNotification("simple-topic", output, "simple");
			return output;
		};
	}

}
