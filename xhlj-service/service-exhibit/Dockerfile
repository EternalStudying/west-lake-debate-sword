FROM jdk17
MAINTAINER swyxl
EXPOSE 8505
ADD target/service-exhibit-1.0-SNAPSHOT.jar /service-exhibit-1.0-SNAPSHOT.jar

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "service-exhibit-1.0-SNAPSHOT.jar"]