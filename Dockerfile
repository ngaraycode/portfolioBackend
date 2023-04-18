FROM amazoncorretto:17-al2-jdk
MAINTAINER NFG
COPY target/NFG-0.0.1-SNAPSHOT.jar nfg-app.jar
ENTRYPOINT ["java","-jar","/nfg-app.jar"]
