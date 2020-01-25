FROM openjdk:8-jre-alpine
MAINTAINER Howard Yoo <yooh@vmware.com>

RUN apk add --no-cache curl

ADD target/fortune-0.0.1-SNAPSHOT.jar app.jar
ADD tracing/opentracing-specialagent-1.5.4.jar opentracing-specialagent-1.5.4.jar
ADD tracing/tracer.properties tracer.properties
ENTRYPOINT ["java", "-javaagent:/opentracing-specialagent-1.5.4.jar", "-Dsa.tracer=wavefront", "-Dsa.config=/tracer.properties", "-Djava.security.egd=file:/dev/./urandom","-Xmx512m", "-jar", "/app.jar"]
