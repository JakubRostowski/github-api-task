# GitHub API task
A simple client for downloading public, non-forked GitHub repositories for a given username.
## Setup, build and run
* Clone repository and enter its directory:
```bash
$ git clone https://github.com/JakubRostowski/github-api-task.git
$ cd github-api-task
```
* Build .jar file:
```bash
$ mvn clean install
```
* Run it:
```bash
$ mvn spring-boot:run
```
* To run unit tests use:
```bash
$ mvn test
```
## API usage
* Application will respond to the following endpoint:
```text
http://localhost:8080/api/github/user/{username}/repositories
```
Remember to replace {username} with the actual GitHub username.
* You can access it via curl:
```
curl -X GET "http://localhost:8080/api/github/user/{username}/repositories" -H "Accept: application/json"
```
