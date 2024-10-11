# Используем обновленный образ Maven для сборки
FROM maven:3.9.0-jdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Используем Tomcat для развертывания WAR файла
FROM tomcat:10.1-jdk17-slim
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/target/*.war app.war

EXPOSE 8080