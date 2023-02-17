FROM eclipse-temurin:17.0.5_8-jre-alpine
RUN mkdir /quarkus-app
WORKDIR /quarkus-app
COPY build/*.jar app.jar
CMD ["java","-jar","app.jar"]
