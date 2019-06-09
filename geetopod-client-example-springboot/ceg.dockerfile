FROM openjdk:12.0.1-jdk-oracle

RUN mkdir -p /opt/ceg

COPY target/geetopod-client-example-springboot-1.0.jar /opt/ceg/ceg.jar
COPY run.sh /opt/ceg/run.sh

EXPOSE 8080

ENV JAVA_OPTS ""

CMD ["/opt/ceg/run.sh"]
