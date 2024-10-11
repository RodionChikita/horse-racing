# Используем обновленный образ Maven для сборки
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Используем Tomcat для развертывания WAR файла
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/target/*.war app.war

EXPOSE 8080