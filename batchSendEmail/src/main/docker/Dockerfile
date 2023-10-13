# 设置基础镜像 jdk8
FROM adoptopenjdk/openjdk8-openj9:alpine-slim
# 维护人员信息
MAINTAINER JinSc
VOLUME /tmp
ENV LANG C.UTF-8
ENV LANGUAGE zh_CN.UTF-8
#这个会引起中文乱码(控制台以及html里面的中文),不知道为何不
#ENV LC_ALL C.UTF-8
ENV TZ Asia/Shanghai
# 设置环境变量
ENV JAVA_OPTS="-server -Xms512m -Xmx512m"
ADD batchsendemail-0.0.1-SNAPSHOT.jar /batchsendemail.jar
ENTRYPOINT java $JAVA_OPTS -Xshareclasses -Xquickstart -Dapollo.access-key.secret=4d3f830824d943beadf33208b194006a -jar /batchsendemail.jar