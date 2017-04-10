# Proxy NBRB

Proxy to interact with a currency provider with The National Bank of the Republic of Belarus (NBRB)


### Developers

- [Anatoly Cherkasov](https://github.com/avcherkasov)


### Interaction

Interaction with the proxy occurs via [JSON][3] objects sent by the [POST method][4] 

The query is the structure of [Context] [1] on the URL `http(s)://{host}:{port}/currency_rates/v1/json` and `http(s)://{host}:{port}/currency_rates/v1/soap`

The answer must come in the structure [ProxyResult] [2]

---
[Change log](CHANGELOG.md)


[1]: ../protocol/src/main/java/io/github/avcherkasov/protocol/model/Context.java
[2]: ../protocol/src/main/java/io/github/avcherkasov/protocol/model/ProxyResult.java
[3]: https://en.wikipedia.org/wiki/JSON
[4]: https://en.wikipedia.org/wiki/POST_(HTTP)
