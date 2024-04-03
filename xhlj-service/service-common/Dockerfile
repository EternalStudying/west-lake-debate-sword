FROM jdk17
MAINTAINER swyxl
EXPOSE 8500
ADD target/service-common-1.0-SNAPSHOT.jar /service-common-1.0-SNAPSHOT.jar

WORKDIR /
ENTRYPOINT ["java" , "-jar" , "service-common-1.0-SNAPSHOT.jar"]