docker build -t bad-java-build -f Dockerfile-b .
docker run --name bad-build bad-java-build
docker cp bad-build:/target/bad-java-test-app-1.0.0.war ./target/bad-java-test-app-1.0.0.war
docker rm bad-build

