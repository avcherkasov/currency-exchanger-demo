# Technical Task


### General description

The service must have the functionality to provide currency exchange rates and convert currency to a specific vendor for a specific date


### Additional requirements

- The service must provide convenient functionality for adding the suppliers currency exchange rates (given that each provider may have its individual settings for interacting with API) and receive currencies at a specified date in automatic mode
- The service should be able to convert currencies from one to another for an arbitrary number of currencies, with specific vendor currency
    - For example: I need to know how many dollars will be 2 euros for the test provider
- At any point in time should be able to enable and disable the currency exchange rates, and also requested from them currency
- The service must be documented and the API to integrate it to anyone
- As a format of interaction to use [JSON][1]
- To store information from suppliers to use [PostgreSQL][2]

[1]: https://en.wikipedia.org/wiki/JSON
[2]: https://www.postgresql.org/
