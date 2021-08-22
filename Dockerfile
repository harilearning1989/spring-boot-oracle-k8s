FROM adoptopenjdk/openjdk11:alpine-jre
#VOLUME /tmp
MAINTAINER haritech.com
ARG JAR_FILE=build/libs/spring-boot-oracle-k8s.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} /var/lib/jenkins/workspace/spring-gradle-docker
ADD ${JAR_FILE} spring-boot-oracle-k8s.jar
ENTRYPOINT ["java","-jar","spring-boot-oracle-k8s.jar"]
EXPOSE 8081
#docker run -d --restart=always -p 8081:8081 harilearning1989/spring-boot-oracle-k8s