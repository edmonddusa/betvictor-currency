# betvictor-currency

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 20](https://jdk.java.net/20/)
- [Maven 3](https://maven.apache.org)

## Building the application locally

To build the application locally and run all tests use command below:
```shell
mvn clean install
``` 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.betvictor.currency.CurrencyApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Configuring the application

The application uses 2 feeds, api.exchangerate.host and openexchangerates.org. Trying to use the first and if this fails using the second. These can be configured in the /src/main/resources/application.properties file. Caching is done by default on a per currency basis and for a maximum time of one minute. Content of properties file below.

```properties
exchangerate.url=https://api.exchangerate.host/latest
openexchangerates.url=https://openexchangerates.org/api/latest.json
openexchangerates.key=8ed1f86e43de4767a3647d7c85cd7dc7
```


## Rest API usage
The application publishes a REST endpoints to convert between international currencies. By default it is running on port 8080. To convert 1000 EURO to USD run:

```shell
http://localhost:8080/eur/usd/1000
```

More then one currency can be converted to by supplying a comma separated list of currencies like:

```shell
http://localhost:8080/eur/usd,sek,nok/1000
```

The output is a array of conversion, one for each currency

```json
[
  {
    "from": {
      "symbol": "EUR",
      "amount": 1000
    },
    "to": {
      "symbol": "USD",
      "amount": 1076.256
    }
  },
  {
    "from": {
      "symbol": "EUR",
      "amount": 1000
    },
    "to": {
      "symbol": "SEK",
      "amount": 11381.762
    }
  },
  {
    "from": {
      "symbol": "EUR",
      "amount": 1000
    },
    "to": {
      "symbol": "NOK",
      "amount": 11749.853
    }
  }
]
```
## Swagger
I tried configuring Swagger 2 but gave up after 4 hours as none of the guides out there seem to be working. Keeps giving me warning of security issues, and none of the endpints and gui is showing up. 


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
