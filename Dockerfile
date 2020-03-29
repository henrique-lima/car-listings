FROM maven:3.6.0-jdk-11-slim AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package
FROM openjdk:11.0.1-jre-slim
ENV ARTIFACT_NAME=car-listings-0.0.1-SNAPSHOT.jar
COPY --from=builder /usr/src/app/target/$ARTIFACT_NAME /usr/app/$ARTIFACT_NAME
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/car-listings-0.0.1-SNAPSHOT.jar"]
CMD java -jar $ARTIFACT_NAME