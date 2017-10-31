# bad-java-test-app
> A test app to simulate bad things

## Setup

This app can be run locally either via a local tomcat server or via [Docker](https://www.docker.com/).

### Local Tomcat Install

Add bad-java-test-app to your tomcat server

### Docker

1. [Install Docker](https://docs.docker.com/engine/installation/)
1. From the app root directory execute: 

```sh
$ /build.sh
$ docker run -d --rm -p 8080:8080 bad-java-test-app 
```

## Usage

In a browser hit the url <http://localhost:8080/bad-java-test-app/behave>. This will
open a page that will refresh every 3 seconds. It will list the associated http
session lifespan (in number of requests), and how much memory has been leaked.

It might also be useful to issue a curl command in a loop, like `while true; do
curl localhost:8080/bad-java-test-app/behave; sleep 3;done`

To initiate leaking issue a request with query string of "leak", like `curl
localhost:8080/bad-java-test-app/behave?leak`

To stop leaking issue a request with a query string of "behave", like `curl
localhost:8080/bad-java-test-app/behave?behave`
