# Technical documentation

API functionality


### Contents

- Database structure
- Vendor
    - Get list of vendors
    - Get list of vendors with pagination
- Rates
- Currency
    - Get currency by ID
    - Get currency by code
    - Get list of currency
    - Get list of currencies with pagination
- Converter
- Common format response with error

---

#### Database structure

```
currency
 - id - The unique identifier
 - char_code - Symbolic currency code
 - num_code - Digital currency code
 - name - Currency name

vendor
 - id - The unique identifier
 - name - Vendor name
 - full_name - Vendor full name
 - url - Url vendor proxy
 - additional - Options for vendor proxy
 - is_active - Active or inactive

vendor_currency
 - id - The unique identifier
 - vendor_id - The unique identifier
 - source_currency_id - The unique identifier
 - target_currency_id - The unique identifier
 - is_active - Active or inactive
 
 rates
  - id - The unique identifier
  - vendor_currency_id - The unique identifier
  - nominal - Currency nominal
  - rate - Currency rate
  - rate_date - Currency rate date
```

#### Vendor

Get list of vendors

*Request*

Method: GET

```
http://{host}:{port}/vendor/v1/list
```

*Response*

```
[
    {
        id: 1,
        name: "cbr",
        url: "http://localhost:8101/currency_rates",
        fullName: "Центральный Банк России",
        additional: "{"param":"params-1","test":"test_2"}",
        active: true
    },
    {
        id: 2,
        name: "test vendor",
        url: "http://localhost:8101/currency_rates",
        fullName: "Test vendor",
        additional: "{"":""}",
        active: false
    }
]
```


Get list of vendors with pagination

*Request*

Method: GET

parameter | description | example
------------ | ------------- | ------------- 
**page** | page number | 1
**limit** | page limit | 2

```
http://{host}:{port}/vendor/v1/list/{page}/{limit}
```

*Response*

```
[
    {
        id: 1,
        name: "cbr",
        url: "http://localhost:8101/currency_rates",
        fullName: "Центральный Банк России",
        additional: "{"param":"params-1","test":"test_2"}",
        active: true
    },
    {
        id: 2,
        name: "test vendor",
        url: "http://localhost:8101/currency_rates",
        fullName: "Test vendor",
        additional: "{"":""}",
        active: false
    }
]
```

---

#### Rates

*Request*

Method: GET

parameter | description | example
------------ | ------------- | ------------- 
**vendor** | vendor of currency rates | cbr
**currency** | currency | USD
**date_request** | date request | 2017-03-21

```
http://{host}:{port}/{path}/{version}/{vendor}/{currency}?date={date_request}
```

*Response*

```
{
    date: "2017-03-21",
    vendor: "Центральный Банк России",
    currencies: [
        {
            numCode: "840",
            charCode: "USD",
            nominal: 1,
            name: "Доллар США",
            rate: 58.3776
        }
    ]
}
```

---

#### Currency

Get currency by ID

*Request*

Method: GET

parameter | description | example
------------ | ------------- | ------------- 
**currency_id** | currency ID | 1

```
http://{host}:{port}/currency/v1/id/{currency_id}
```

*Response*

```
{
    id: 1,
    charCode: "RUB",
    numCode: "643",
    name: "Российский рубль"
}
```


Get currency by code

*Request*

Method: GET

parameter | description | example
------------ | ------------- | ------------- 
**currency_code** | currency code | RUB or 643

```
http://{host}:{port}/currency/v1/id/{currency_code}
```

*Response*

```
{
    id: 1,
    charCode: "RUB",
    numCode: "643",
    name: "Российский рубль"
}
```


Get list of currency

*Request*

Method: GET

```
http://{host}:{port}/currency/v1
```


*Response*

```
[
    {
        id: 1,
        charCode: "RUB",
        numCode: "643",
        name: "Российский рубль"
    },
    {
        id: 2,
        charCode: "USD",
        numCode: "840",
        name: "Доллар США"
    }
]
```


Get list of currencies with pagination

*Request*

Method: GET

parameter | description | example
------------ | ------------- | ------------- 
**page** | page number | 1
**limit** | page limit | 2

```
http://{host}:{port}/currency/v1/list/{page}/{limit}
```

*Response*

```
[
    {
        id: 1,
        charCode: "RUB",
        numCode: "643",
        name: "Российский рубль"
    },
    {
        id: 2,
        charCode: "USD",
        numCode: "840",
        name: "Доллар США"
    }
]
```

---

#### Converter

*Request*

Method: GET

parameter | description | example
------------ | ------------- | ------------- 
**vendor** | vendor of currency rates | cbr
**from_currency** | from currency | RUB
**to_currency** | to currency | USD
**date_request** | date request | 2017-03-21
**count** | count currency | 1

```
http://{host}:{port}/{path}/{version}/{vendor}/{from_currency}/{to_currency}/{date_request}/{count}
```

*Response*

```
{
    vendor: "Центральный Банк России",
    from: "USD",
    to: "RUB",
    rate: 116.7552,
    date: 2017-03-21,
    count: 2
}
```

---

#### Common format response with error

```
{
    error: {
        code: "some error code",
        message: "some error message"
    }
}
```
