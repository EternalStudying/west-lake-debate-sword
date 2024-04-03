FROM jdk17
MAINTAINER swyxl
EXPOSE 8999
ADD target/xhlj-service-gateway-1.0-SNAPSHOT.jar /xhlj-service-gateway-1.0-SNAPSHOT.jar

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "xhlj-service-gateway-1.0-SNAPSHOT.jar"]