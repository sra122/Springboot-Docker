FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/spring-mysql-service.jar application.jar
EXPOSE 8011
CMD ["java", "-jar", "application.jar"]
