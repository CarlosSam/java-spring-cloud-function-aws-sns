package com.flama.javaSpringCloudFunction.javaspringcloudfunctionawssns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class JavaSpringCloudFunctionApplicationAwsSns {

	private static final Logger log = LoggerFactory.getLogger(JavaSpringCloudFunctionApplicationAwsSns.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringCloudFunctionApplicationAwsSns.class, args);
	}

	@Bean
	public Function<Map, Map> hello(){
		return input -> {
			log.info("hello with input: {}", input);
			Map output = new HashMap();
			output.put("greeting", "Hello " + input.get("name") + " from a map");
			return output;
		};
	}

}
