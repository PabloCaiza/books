#FROM eclipse-temurin:17.0.5_8-jre-alpine
FROM arm64v8/eclipse-temurin:17
RUN mkdir /app
WORKDIR /app
COPY build/libs/app-web.jar app.jar
COPY build/libs/libs ./libs
CMD ["java","-jar","app.jar"]