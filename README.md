# WalletTransfer
A RESTful API to facilitate Wallet Transfer.


### Technologies
- JAX-RS API - Jersey Implemetation
- MySql
- GlassFish
- Cucumber


### How to run
The root Url
```sh
http://node27266-wallettransfer.j.scaleforce.net/wallet
```


### Available Services

| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /account/{email} | check Balance |
| POST | /register/{email} | register account  |
| GET | /transactions/{email} | check Latest Transaction |
| POST | /transaction/{transferer}/{transferee}/{amount} | Transfer Amount |

### Http Status
- 200 OK: The request has succeeded
- 400 Bad Request: The request could not be understood by the server
- 404 Not Found: The requested resource cannot be found
- 500 Internal Server Error: The server encountered an unexpected condition

### Sample
##### Register :
```sh
{
    "success": true,
    "balance" : 10000.0
}
```
##### check Balance :

```sh
{
    "success": true,
    "balance" : 10000.0
}
```

#### Latest Transactions:
```sh
{
    "success": true,
    "transactions": [
    {
        "id": "1",
        "from" : "test@test.com",
        "to": "test1@test.com",
        "type": "transfer",
        "amount": 100.0,
        "datetime": "2018-04-16T19:20+08:00"
    },
    {
        "id": "2",
        "from" : "test1@test.com",
        "to": "test@test.com",
        "type": "transfer",
        "amount": 8.0,
        "datetime": "2018-04-17T12:20+08:00"
    }
    ]
}
```

##### Transfer :

```sh
{
    "success": true,
}
```