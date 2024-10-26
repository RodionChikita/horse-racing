FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Шаг сборки окончательного образа
FROM openjdk:17-jdk
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar demo.jar

ENTRYPOINT ["java", "-jar", "demo.jar"]