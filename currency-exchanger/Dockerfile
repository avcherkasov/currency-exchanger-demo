FROM openjdk:8

MAINTAINER Anatoly Cherkasov, https://github.com/avcherkasov

COPY @artifactId@-@version@.jar /opt/@artifactId@/@artifactId@.jar

CMD ["java", "-Xmx512m", "-jar","/opt/@artifactId@/@artifactId@.jar"]

EXPOSE 8090

WORKDIR /opt/@artifactId@
