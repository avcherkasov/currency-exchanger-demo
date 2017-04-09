FROM frolvlad/alpine-oraclejdk8

MAINTAINER Anatoly Cherkasov

COPY @artifactId@-@version@.jar /opt/@artifactId@/@artifactId@.jar

CMD ["java", "-Xmx512m", "-jar","/opt/@artifactId@/@artifactId@.jar"]

EXPOSE 8101

WORKDIR /opt/@artifactId@
