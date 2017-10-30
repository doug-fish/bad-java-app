FROM tomcat:7-jre8-alpine
CMD ["catalina.sh", "run"]
COPY ./target/bad-java-test-app-1.0.0.war /usr/local/tomcat/webapps/bad-java-test-app.war