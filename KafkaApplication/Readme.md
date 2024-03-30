# **Kafka Application**

## _**Components of Event Driven Application**_

    1. Producer
    2. Consumer
    3. Topic
    4. Payload


**@Annotation Used** 

    @KafkaListener(id = "orderConsumerClient", topics = "order.created",
            groupId = "dispatch.order.created.consumer")

##### Application property to set **what type of data process** in payload

    spring.kafka.consumer.auto-offset-reset=earliest
    spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer