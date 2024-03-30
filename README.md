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

### **Sample from CMD**

    E:\kafka>.\bin\windows\kafka-console-producer.bat --topic order.created --bootstrap-server localhost:9092
    >{"uuid":"62968107-df94-44e9-b2fb-3d6cc0b7b722","item":"book"}
    >{"uuid":"62968107-df94-44e9-b2fb-3d6cc0b7b722","item":"book"}
    >{"uuid":"62968107-df94-44e9-b2fb-3d6cc0b7b722","item":"book2"}
    >{"uuid":"62968107-df94-44e9-b2fb-3d6c2","item":"book"}


## Two-ways to do kafka setup Basic and Standard way.

### **Basic one** 
Through application.properties. _Drawback_ _Single Instance Static Props_

    spring.kafka.consumer.auto-offset-reset=earliest
    spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
    spring.kafka.consumer.properties.spring.deserializer.value.delegate.class=org.springframework.kafka.support.serializer.JsonDeserializer
    spring.kafka.consumer.properties.spring.json.value.default.type = com.basic.demo.message.OrderCreated

### Standard One

Through Code **Dynamic Props based on Env** Create Config file and add component scan on it.

```java
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
//        Mandatory to provide Server
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        Who will Handle error
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        What type of error handle
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//        What type of JSON data Come
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderCreated.class.getCanonicalName());
//        Other data type to process
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        Return Factory
        return new DefaultKafkaConsumerFactory<>(config);
    }
```

**Place below code in listener**

```java
    @KafkaListener(id = "orderConsumerClient", topics = "order.created",
            groupId = "dispatch.order.created.consumer",
    containerFactory = "kafkaListenerContainerFactory")
    public void listen(OrderCreated payload) {
    log.info("Message Received " + payload);
//    your process with data
}

```
