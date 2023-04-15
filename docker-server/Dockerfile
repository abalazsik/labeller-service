FROM openjdk:13

RUN mkdir /app

COPY ./application.properties /app
COPY ./labeller-service.jar /app

EXPOSE 8080

CMD ["java", "-jar", "/app/labeller-service.jar", "--spring.config.location=file:///app/application.properties"]
