# REST API Geniusee test task

The REST API to the example app is described below.


## Get list of movies or orders

### Request

`GET /api/orders, /api/movies`
```
curl -X GET \
  http://localhost:8080/api/movies \
  -H 'content-type: application/json'
```

To specify page number or page size, request should look like 

`GET /api/orders?page=0&size=5`

Defaults values are `page=0` and `size=3`.

To search by any parameter of an entity the request should look like

`GET /api/movies?key=name$value=Soul`,
where key is name of an entity field and value is a value of this field

### Response
 
```
[
    {
        "id": 1,
        "name": "Pirates Of Caribbean",
        "rating": 9.1,
        "orderId": 1
    },
    {
        "id": 2,
        "name": "Game of Thrones",
        "rating": 9.2,
        "orderId": 2
    }
]
```

## Get movie or order by id

### Request

`GET /api/order/{id}, /api/movie/{id}`

```
curl -X GET \
  http://localhost:8080/api/movie/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 5bebd78f-705e-6231-7b02-c6454f929eaa'
```

### Response
```
{
    "id": 1,
    "name": "Pirates Of Caribbean",
    "rating": 9.1,
    "orderId": 1
}
```
## Create new order or movie

### Request

`POST /api/movie, /api/order`

```
curl -X POST \
  http://localhost:8080/api/movie \
  -H 'content-type: application/json' \
  -d '{
    "name": "Pirates Of Caribbean",
    "rating": 6,
    "orderId": 1
}'
```

### Response

```
{
    "id": 7,
    "name": "Pirates Of Caribbean",
    "rating": 6,
    "orderId": 1
}
```

## Update existing entity

### Request

`PUT /api/movie`
```
curl -X PUT \
  http://localhost:8080/api/movie \
  -H 'content-type: application/json' \
  -d '{
    "id": 7,
    "name": "Pirates Of Caribbean 2",
    "rating": 4,
    "orderId": 1
}'
```

### Response

```
{
    "id": 7,
    "name": "Pirates Of Caribbean 2",
    "rating": 4,
    "orderId": 1
}
```

