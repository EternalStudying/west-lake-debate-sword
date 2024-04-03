FROM jdk17
MAINTAINER swyxl
EXPOSE 8504
ADD target/service-active-1.0-SNAPSHOT.jar /service-active-1.0-SNAPSHOT.jar

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "service-active-1.0-SNAPSHOT.jar"]