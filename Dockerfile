FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/hello-jenkins-demo.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]