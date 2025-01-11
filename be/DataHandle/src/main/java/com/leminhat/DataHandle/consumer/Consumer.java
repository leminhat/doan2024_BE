package com.leminhat.DataHandle.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leminhat.DataHandle.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "Ecommerce.ecommerce.product", groupId = "my-consumer-group")
    public void listenProduct(String rawData) {
        try {
            JsonNode rootNode = objectMapper.readTree(rawData);
            JsonNode payload = rootNode.get("payload");

            String operation = payload.get("op").asText();

            JsonNode beforeNode = payload.get("before");
            JsonNode afterNode = payload.get("after");

            log.info(objectMapper.writeValueAsString(beforeNode));

            switch (operation) {
                case "c": // Create
                    log.info("Create operation detected");
//                    handleCreate(String.valueOf(afterNode);
                    break;
                case "u": // Update
                    log.info("Update operation detected");
//                    handleUpdate(String.valueOf(beforeNode), String.valueOf(afterNode));
                    break;
                case "d": // Delete
                    log.info("Delete operation detected");
//                    handleDelete(String.valueOf(beforeNode), schema);
                    break;
                default:
                    System.out.println("Unknown operation: " + operation);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "Ecommerce.ecommerce.orders", groupId = "my-consumer-group")
    public void listenOrder(String rawData) {
        if (rawData != null) {
            try {
                JsonNode rootNode = objectMapper.readTree(rawData);
                JsonNode payload = rootNode.get("payload");

                String operation = payload.get("op").asText();

                JsonNode beforeNode = payload.get("before");
                JsonNode afterNode = payload.get("after");


                log.info(objectMapper.writeValueAsString(beforeNode));

                switch (operation) {
                    case "c": // Create
                        log.info("Create operation detected");
                        orderService.handleCreate(String.valueOf(afterNode));
                        break;
                    case "u": // Update
                        log.info("Update operation detected");
                        orderService.handleUpdate(String.valueOf(beforeNode), String.valueOf(afterNode));
                        break;
                    case "d": // Delete
                        log.info("Delete operation detected");
//                    handleDelete(String.valueOf(beforeNode), schema);
                        break;
                    case "r": // read
                        log.info("Read operation detected");
                        orderService.handleCreate(String.valueOf(afterNode));
                        break;
                    default:
                        System.out.println("Unknown operation: " + operation);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
