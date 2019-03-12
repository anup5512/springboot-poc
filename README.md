# springboot-poc

## What does this project include?
1. Health Check API
2. Few Github APIs
3. Lombok library (You might have to enable Annotation Processor if using IntelliJ Idea)  
4. Springfox Swagger2 library to generate Swagger spec  
  4.1 UI - http://localhost:8080/swagger-ui.html  
  4.2 Spec - http://localhost:8080/v2/api-docs  
5. GsonHttpMessageConverter configured  
  5.1 SpringfoxJsonToGsonAdapter to convert Springfox Json to Google JsonElement    
  5.2 Also, done using 'spring.gson' application properties  
6. Declarative Feign HTTP Client (provided by Spring Cloud and Netflix)    
7. Timezone configuration  
8. HTTP request interceptor  
9. Guava Cache  
10. Spring Hateoas  
11. API Exception Handling 

## TODO
~0. Exception handling~
1. Retry mechanism for failed HTTP requests  
2. Circuit breaker mechanism in case of downstream failure  
3. Persist data in MongoDb  
4. Spock test cases  
5. Flux/Mono implementation  
6. Disruptor implementation  
7. Hazelcast implementation  
8. RabbitMQ interaction  
9. Kafka interaction  
!!!
