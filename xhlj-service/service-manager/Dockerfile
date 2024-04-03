FROM jdk17
MAINTAINER swyxl
EXPOSE 8503
ADD target/service-manager-1.0-SNAPSHOT.jar /service-manager-1.0-SNAPSHOT.jar

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "service-manager-1.0-SNAPSHOT.jar"]