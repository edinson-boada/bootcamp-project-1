FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/*.jar /usr/share/credit-bank-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/share/credit-bank-service.jar"]
