FROM java:11-jre
EXPOSE 8085
ADD target/subssrv-docker.jar subsserv-docker.jar
ENTRYPOINT ["java","-jar","/subsserv-docker.jar"]