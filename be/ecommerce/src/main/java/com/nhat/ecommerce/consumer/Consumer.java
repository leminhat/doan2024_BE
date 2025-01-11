//package com.nhat.ecommerce.consumer;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.kafka.annotation.KafkaListener;
//
//@Service
//public class Consumer {
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @KafkaListener(topics = "Ecommerce.ecommerce.product", groupId = "my-consumer-group")
//    public void listenProduct(String rawData) {
//        try {
//            JsonNode rootNode = objectMapper.readTree(rawData);
//            JsonNode payload = rootNode.get("payload");
//
//            String operation = payload.get("op").asText();
//
//            JsonNode beforeNode = payload.get("before");
//            JsonNode afterNode = payload.get("after");
//
//            log.info(objectMapper.writeValueAsString(beforeNode));
//
//            switch (operation) {
//                case "c": // Create
//                    log.info("Create operation detected");
////                    handleCreate(String.valueOf(afterNode);
//                    break;
//                case "u": // Update
//                    log.info("Update operation detected");
////                    handleUpdate(String.valueOf(beforeNode), String.valueOf(afterNode));
//                    break;
//                case "d": // Delete
//                    log.info("Delete operation detected");
////                    handleDelete(String.valueOf(beforeNode), schema);
//                    break;
//                default:
//                    System.out.println("Unknown operation: " + operation);
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error handle: " + ExceptionUtils.getStackTrace(e));
//        }
//
//    }
//
//
//
//}
