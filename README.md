# betvictor-currency

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 20](https://jdk.java.net/20/)
- [Maven 3](https://maven.apache.org)

## Building the application locally

To build the application locally and run all test use command below:
```shell
mvn clean install
``` 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.betvictor.currency.CurrencyApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
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


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
