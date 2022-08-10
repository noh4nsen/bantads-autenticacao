FROM maven:3.8.6-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM gcr.io/distroless/java11-debian11
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8080
CMD ["app.jar"]