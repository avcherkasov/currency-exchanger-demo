# Currency Exchanger Demo

Demonstration of currency exchanger


### Developers

- [Anatoly Cherkasov](https://github.com/avcherkasov)


### Requirements

- [Java 8][1]
- [PostgreSQL][2]


### Contents

1. [Protocol](protocol/README.md) - Definition of the protocol of interaction
1. [Proxy CBR](proxy-cbr/README.md) - Proxy to interact with a currency provider with Central Bank of Russia (CBR)
1. [Currency exchanger](currency-exchanger/README.md) - Produces data on currency and its conversion
1. [Spring boot admin](spring-boot-admin/README.md) - Spring Boot Admin is a simple application to manage and monitor your Spring Boot Applications


### Preparing for demo

1. Run all applications, they will be available on the URL:
    - [http://127.0.0.1:8101](http://127.0.0.1:8101) - Proxy CBR
    - [http://127.0.0.1:8102](http://127.0.0.1:8102) - Proxy NBRB
    - [http://127.0.0.1:8090](http://127.0.0.1:8090) - Currency exchanger
    - [http://127.0.0.1:8300](http://127.0.0.1:8300) - Spring boot admin
1. Automatically for the current date for the proxy will take currency rates
1. We go through the links and demonstrate the basic functionality
    - [Output of all currencies](http://127.0.0.1:8090/currency/v1/list)
    - [Output of all suppliers](http://127.0.0.1:8090/vendor/v1/list)
    - Currency conversion
        - [USD -> EUR](http://127.0.0.1:8090/converter/v1/cbr/usd/eur/2017-04-05/1)
        - [EUR -> USD](http://127.0.0.1:8090/converter/v1/cbr/eur/usd/2017-04-05/1)
        - [RUB -> USD](http://127.0.0.1:8090/converter/v1/cbr/rub/usd/2017-04-05/1)
        - [USD -> RUB](http://127.0.0.1:8090/converter/v1/cbr/usd/rub/2017-04-05/1)
        - [RUB -> RUB](http://127.0.0.1:8090/converter/v1/cbr/rub/rub/2017-04-05/1)
    - [Display currency rates for the current date](http://127.0.0.1:8090/rates/v1/cbr/usd?date=2017-04-05)
    - [Show error output](http://127.0.0.1:8090/rates/v1/bad/bad)
1. Disable the database for an arbitrary currency for a particular vendor and show that it no longer gets
1. We disable in the database of a particular vendor and show that it no longer participates in the exchange rate
1. Turn everything back on
1. Show how to quickly add a new type of currency to the database and bind it to the supplier to get the exchange rate
1. Show how to quickly connect a new exchange rate provider proxy with individual proxy settings and how on the fly you can change the URL (Proxy NBRB)
    - http://127.0.0.1:8102/currency_rates/v1/json
    - http://127.0.0.1:8102/currency_rates/v1/soap
1. We show in the database records for rates tied to specific suppliers
1. Show the status of running applications in a [spring boot admin](http://127.0.0.1:8300)
    - Default access: admin / admin

**In conclusion**:
- All the interaction of exchange rates with proxies is built on a single protocol in the same data format, which allows you to implement vendor proxy in any language by implementing a single protocol of interaction
- All settings are in the database, which does not require stopping and launching the application
- Integration is possible in any language


### What else can be improved

- Add caching
- Think over the protocol of interaction more carefully
- Implement a helper library to work with the date on proxy, so as not to duplicate
- Receiving a currency on a schedule based on the configuration in the database
- Personal cabinet for managing data in the database with access by roles
- Transfer of incoming currency to a minor unit
- Start in the container and check the start of the container
- Prepare a docker-composer
- To work out possible errors and their correct output
- Interact with the database to implement on JDBC
- Beautiful documentation
- Add CI
- And much more

---
[Change log](CHANGELOG.md)


[1]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[2]: https://www.postgresql.org/
