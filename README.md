# bad-java-test-app
> A test app to simulate bad things

## Setup

This app can be run locally either via a local tomcat server or via [Docker](https://www.docker.com/).

### Local Tomcat Install

Add bad-java-test-app to your tomcat server

### Docker

1. [Install Docker](https://docs.docker.com/engine/installation/)
2. From the app root directory execute: 

```sh
$ docker build -t bad-java-test-app .
$ docker run -it --rm -p 8080:8080 bad-java-test-app 
```

## Usage

In a browser hit the url [http://localhost:8080/bad-java-test-app/hello](http://localhost:8080/bad-java-test-app/hello)