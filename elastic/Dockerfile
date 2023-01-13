FROM amazoncorretto:11

WORKDIR /app

COPY target/elastic-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]