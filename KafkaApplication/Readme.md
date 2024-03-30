# **Kafka Application**


.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

.\bin\windows\kafka-server-start.bat .\config\server.properties

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


**To handle invalid json need to add Error handler**

    spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.
    
**ErrorHandlingDeserializer**

    spring.kafka.consumer.properties.spring.deserializer.value.delegate.class=org.springframework.kafka.support.serializer.
**JsonDeserializer**

valid JSON

    {"uuid":"62968107-df94-44e9-b2fb-3d6cc0b7b722","item":"book2"}

Invalid JSON
    
    {"uuid":"62968107-df94-44e9-b2fb-3d6c2","item":"book"}