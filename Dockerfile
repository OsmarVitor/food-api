FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/*.jar /app/food-api.jar

EXPOSE 8080

CMD ["java", "-jar", "food-api.jar"]




# docker image build -t foodapi .

# docker container run --rm -p 8080:8080 foodapi
# docker container run --rm -p 8080:8080 foodapi