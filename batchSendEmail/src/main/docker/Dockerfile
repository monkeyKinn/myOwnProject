FROM openjdk:8
VOLUME /tmp
ENV LANG C.UTF-8
ENV LANGUAGE zh_CN.UTF-8
ENV LC_ALL C.UTF-8
ENV TZ Asia/Shanghai
ADD batchsendemail-0.0.1-SNAPSHOT.jar /batchsendemail.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/batchsendemail.jar"]