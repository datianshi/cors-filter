backbone-mvc-rest
=================

The front end is pure javascript bootstrapped. That has CRUD operations to the restful service.

The restful service is using the spring mvc to expose CRUD consumed by the above operation. The database
is simulated just by a simple in memory hash map.

To start the server:
mvn tomcat7:run

Go to url: http://localhost:8080/app/ and choose the operation you want to do.
