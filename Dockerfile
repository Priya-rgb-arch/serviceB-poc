FROM tomcat:latest
EXPOSE 8080
ADD target/serviceb-poc.jar serviceb-poc.jar
ENTRYPOINT ["java","-jar","serviceb-poc.jar"]
