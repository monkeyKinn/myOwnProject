FROM java:8
VOLUME /tmp
ADD batchsendemail-0.0.1-SNAPSHOT.jar /batchsendemail.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/batchsendemail.jar"]