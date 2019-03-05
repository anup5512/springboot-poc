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
6. Declarative Feign HTTP Client (provided by Spring Cloud and Netflix)

!!!