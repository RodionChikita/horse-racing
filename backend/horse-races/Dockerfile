# Шаг сборки 
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Шаг сборки окончательного образа
FROM openjdk:17-jdk
WORKDIR /usr/local/lib
# Убедитесь, что путь к файлу корректен
COPY --from=build /home/app/target/*.jar demo.jar

ENTRYPOINT ["java", "-jar", "demo.jar"]
