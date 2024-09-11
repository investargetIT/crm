FROM amazoncorretto:8-alpine-jre
COPY target/*.jar app.jar
EXPOSE 8083
CMD ["java","-jar","app.jar"]
