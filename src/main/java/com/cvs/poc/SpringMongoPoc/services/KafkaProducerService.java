package com.cvs.poc.SpringMongoPoc.services;

import com.cvs.poc.SpringMongoPoc.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topics.add-delete-ssk}")
    private String topicName;

    public void savePerson(Person person) {

        try {


            Message<String> message = MessageBuilder
                    .withPayload(generateEPHMessagePayload(person))
                    .setHeader(KafkaHeaders.MESSAGE_KEY, person.getId())
                    .setHeader(KafkaHeaders.TOPIC, topicName)
                    .build();

            SendResult<String, String> res = kafkaTemplate.send(message).get();

            kafkaTemplate.flush();
            System.out.println(String.format("sending message='{}' to topic='{}'", res, topicName)); // for local testing
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private String generateEPHMessagePayload(Person person) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();


        String personMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);

        return personMessage;
    }
}
