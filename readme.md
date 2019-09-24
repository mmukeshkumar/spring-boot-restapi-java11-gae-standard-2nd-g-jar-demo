# Description:
Demo Java 11 maven project showcasing Spring boot, Spring cloud GCP, Cloud Pub/Sub, Stackdriver, it can be deployed to gae-standard
It demos adding message to Spring cloud GCP topic and pulling messages from a topic by subscribing to the topic
It also demos distributed tracing, co-relating logs in cases where one request spawns multiple requests across multiple micro services

# Prerequisites 
1. Install Java 11 JDK
2. Set up a project in GCP through https://cloud.google.com/ or gcloud CLI
3. Enable billing

# Building
./mvnw clean install

# Running locally: 
java -jar target/spring-boot-gcp-java11-stackdriver-tracing-logging-demo-0.0.1-SNAPSHOT.jar
OR import project into Intellij IDE and run the GcpDemoApplication as a Java application

# Testing locally:
List REST endpoints
curl -i http://localhost:8080
List orders
curl -i http://localhost:8080/orders

# Deploying to google app engine:
./mvnw package appengine:deploy -Dapp.deploy.projectId=[your-gcp-project-name]

#sample curl requests
curl -i -XPOST \
-H "Content-Type: application/json" \
-d '{"firstName":"Larry","lastName":"Grooves","customerId":"53a2e699-0205-4546-aae6-8fc903c478c7","orderDate":"2019-10-10T18:06:48.526+0000"}' \
http://localhost:8080/orders

curl -X POST \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{"message": "Hello"}' \
http://localhost:8080/greet

curl -X POST \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{"message": "Hello"}' \
http://localhost:8080/greetAsync 


#submit request and get trace Id back
curl -i -XPOST \
-H "Content-Type: application/json" \
-d '{"firstName":"Larry","lastName":"Grooves","customerId":"53a2e699-0205-4546-aae6-8fc903c478c7","orderDate":"2019-10-10T18:06:48.526+0000"}' \
https://spring-boot-gcp-demo-251616.appspot.com/orders

HTTP/2 201
set-cookie: JSESSIONID=node0piy6x6ldeidw14trhe11h2axe3.node0; Path=/
expires: Thu, 01 Jan 1970 00:00:00 GMT
content-type: text/plain;charset=utf-8
x-cloud-trace-context: 46dc8f6388fadba0d182970fbf88d792;o=1
date: Tue, 10 Sep 2019 20:38:42 GMT
server: Google Frontend
content-length: 36
alt-svc: quic=":443"; ma=2592000; v="46,43,39"

9b2c6a02-119d-4f1a-b781-de6ccad32689

Advanced search entry in stackdriver logviewer
(trace="projects/spring-boot-gcp-demo-251616/traces/46dc8f6388fadba0d182970fbf88d792")

# force a trace Id:
curl -i -XPOST \
-H "Content-Type: application/json" \
-H "X-Cloud-Trace-Context: 105445aa7843bc8bf206b120001000" \
-d '{"firstName":"My","lastName":"Nyugen","customerId":"53a2e698-0205-4546-aae6-8fc903c478c7","orderDate":"2019-10-10T18:06:48.526+0000"}' \
https://spring-boot-gcp-demo-251616.appspot.com/orders
##  Note: This wont work for some reason, the passed in X-Cloud-Trace-Context does not get used


# Issues/Todo
 * force a trace Id wont work, the passed in X-Cloud-Trace-Context does not get used, instead a new X-Cloud-Trace-Context gets used and returned
 * when deployed to app engine standard, its takes upto 30 seconds to start an instances since all instances get shutdown when no requests come in
   for certain period of time









