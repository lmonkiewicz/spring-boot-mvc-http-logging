# spring-boot-mvc-http-logging
Example of HTTP logging of Spring MVC RestControllers and RestTemplates traffic.


### How to run
As standard _spring-boot_ app:

<code>
  mvn clean install spring-boot:run
</code>


For _war_ deployment using _cargo_ with _tomcat8x_ container, use:

<code>
mvn clean install cargo:run
</code>

Integration test is configured to run on deployed application on Tomcat 8 using cargo plugin, and tested with REST-assured.

