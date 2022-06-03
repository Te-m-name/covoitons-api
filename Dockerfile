FROM openjdk:18-oracle
VOLUME /tmp
ARG JAR_FILE
COPY target/covoitons-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]