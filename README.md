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

In a browser hit the url <http://localhost:8080/bad-java-test-app/test>. This will
open a page that will refresh every 3 seconds. It will show
1. The Session ID to help identify when a different or restarted servlet is accessed
1. The session request lifespan which is a count of the http requests that have been backed by the sam ehttp session. This is to identify discontinuities in the user experience becuase a session has been lost/reset
1. the mode the test is operating in (leak, slow, behave)
1. The current number of characters held in memorhy
1. The current milliseconds of delay added to each request

It might also be useful to issue a curl command in a loop, like `while true; do
curl localhost:8080/bad-java-test-app/test; sleep 3;done` This can display the same data. Note that each request will get a new session, so the session reuest lifespand won't be useful.

To initiate leaking issue a request with query string of "leak", like `curl
localhost:8080/bad-java-test-app/test?leak`

To initiate slowness issue a request with query string of "slow", like `curl
localhost:8080/bad-java-test-app/test?slow`

To stop leaking and slowness issue a request with a query string of "test", like `curl
localhost:8080/bad-java-test-app/test?behave`
