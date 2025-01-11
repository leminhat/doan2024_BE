package com.leminhat.DataHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataHandleApplication {

	private static final Logger log = LoggerFactory.getLogger(DataHandleApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(DataHandleApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(Consumer consumer) throws Exception {
//		return args -> {
//			log.info("Test Here!");
//			consumer.listenOrder(mess);
//		};
//	}



}
