# microservice-auth-java
Java spring boot authentication server
# Technologies
- Java 8
- Spring boot latest version
- Mysql for persisting data
- Redis cluster for caching data
- Spring boot oauth2
# How to install?
```
$ docker-compose up -d
```
# How get token?
you can get access_token and refresh_token by this endpoint
```
URL: http://host:port/oauth/token
Method: POST

- Headers:
Authorization: Basic base64Encode(client_id:client_secret)

- Body
username: username
password: password
grant_type: password
```
#### Response sample:
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQVVUSEVOVElDQVRJT04iXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTczODUwMTc5LCJ1c2VySWQiOjEsImF1dGhvcml0aWVzIjpbIlJPTEVfU1VQRVJfQURNSU4iXSwianRpIjoiN2UyMTE0ZjAtMzI1Yi00ZTU0LThiNzItNmMyNjk3YTBhNzMwIiwiY2xpZW50X2lkIjoiY2xpZW50In0.cSjk_UHbVSiFVG3EPefdKammGJ7o6zWKPMMIYrwbr-7rwhOZafD9aCAt_YUOr9DE0QNT-bkCBBdrmsvwZxmksmK3WlTjI7GjTrykMz5Zk4J-T5W1xavovzjpz2B-sDaqJhmkjyVEEpsDBWUHqGVlcoyCda3N0ixmZrwlkxVG4dFpIlSExLlF1L3Q5u2G7bWZntZnJ9QNP9cylXXXdBF40RvI-VqgaSecuu99x-uY0Eynr7SnFOzfMEVrknqT7ZJzkcA9hPm6z6ykQ4yZc5gav2cbGKKwFfcBRYsU1rHiwvqomlNhC8Djb0w5pkfFoxyTQar_mbP3R6wTRsIAKKWIZQ",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQVVUSEVOVElDQVRJT04iXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiN2UyMTE0ZjAtMzI1Yi00ZTU0LThiNzItNmMyNjk3YTBhNzMwIiwiZXhwIjoxNTczODUzNzc5LCJ1c2VySWQiOjEsImF1dGhvcml0aWVzIjpbIlJPTEVfU1VQRVJfQURNSU4iXSwianRpIjoiZWY0NzgzNGQtOTkwNi00NDlmLWJlY2YtMjE1YTg3YzIyNjAxIiwiY2xpZW50X2lkIjoiY2xpZW50In0.IXv_SPT9tKEEh0lMy1SD22EPu9MsUGomGNGrlqbhmBN6B22mM5xBAbw8WRjJoeMfOMLroNiNqJJ-zFy2CuWP52D5fxpgQkRjmjG9_nX9miXtNguxafkhM2qzCmxRebHrzyp40wpBBFia37a8DVHjh-7DbmJahl60PHBvjNiyjmzancL6Tymmpajz1Wb2Z8yt-WqLK-sP46aX6Lti9GNRFXywOZFeJLlv8ydS8FEbEe7ZcztWG3Trr6KO4ceCwNE01eBnrAxcWbYWclm-334f-JZdtGfdAFwuvTYYVtuIj8PbJaxtmrZ7o1sT_Bm4zZq-kfNCR0MGvoHEvKepV9Bvsw",
    "expires_in": 3599,
    "scope": "read write",
    "userId": 1,
    "jti": "7e2114f0-325b-4e54-8b72-6c2697a0a730"
}
```

# How refresh token?
you can refresh expired token and get new access_token and refresh_token
```
URL: http://host:port/oauth/token
Method: POST

- Headers:
Authorization: Basic base64Encode(client_id:client_secret)

- Body
refresh_token: eyJhbGciOiJSUzI1NiIsIn.....
grant_type: refresh_token
```
#### Response sample:
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQVVUSEVOVElDQVRJT04iXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTczODUwMTc0LCJhdXRob3JpdGllcyI6WyJST0xFX1NVUEVSX0FETUlOIl0sImp0aSI6IjMwNTEyYzE3LWQ2YTYtNGFkNy04MDk2LWUxOTNlMzMyNGM1YiIsImNsaWVudF9pZCI6ImNsaWVudCJ9.eqEdQD4scVIzui3ZC_d6T2jXLkN6XApZ2zhEi8roeUkeTAocUyq7rrAi4a65-vPaSvaoK81cwTuZ3y7XKCG_e1CrLHMQYcHNXuVDB6QboUSOLvXdEgXT1eKMlGTG6UhYJPcsPzzHXRFOlBXvMwKS2ls2kw5VIwq4o2hAfmCkhSBiITTGooEAORMZ6tjFOkaqJ-COhA4OHh_Td4LnYqVlIaZUDt31cEZRkuu5aV2X7pb7LDzFxpROw9UMZtntx5iX5iXzoUwwwIBNWjvYqsC-Vi9EGlHzbiKg69iBMWlXp_ArQHe7t357uX5_79XjWeiHyalex4fWvOnBeX1fQU1Qzg",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQVVUSEVOVElDQVRJT04iXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiMzA1MTJjMTctZDZhNi00YWQ3LTgwOTYtZTE5M2UzMzI0YzViIiwiZXhwIjoxNTczODUzNzc0LCJhdXRob3JpdGllcyI6WyJST0xFX1NVUEVSX0FETUlOIl0sImp0aSI6Ijk3MzQxNDRmLTc3ZjYtNDA0ZS05YzczLTdkNzMwMjIxZGUzNCIsImNsaWVudF9pZCI6ImNsaWVudCJ9.MakiM6rjZHazXJP3JlWKuxCCFhF-NtBMOAM84RS1ruA0DS8EOLbynXVTSyBQo381jztpny5Mda2fEOwY7lg-KQItdVuJ5bgtJK3_9sZHPQxOIHDx3MVDHN5F83hLFpceKZ9zDYJ81pKWWdIRczFDG_XYCgBFDany7m9cgBd4cWwU2KtlIBo8CHaDYvQit2Na-IPhueil6tlYJ5u3CjURPSfS-VIf6Xo4w2X9YqHlzJNIC2Vbfv6q3Em3PllASz2nLWLvB02Vl1yLXwiwA6UrZPXLHuEzzBm1sCy4N7pTvayDnSRXWULqQkudsKmLxdM_zR4rfi3pFrN1wY6FV40zjw",
    "expires_in": 3599,
    "scope": "read write",
    "jti": "30512c17-d6a6-4ad7-8096-e193e3324c5b"
}
```

# How check token validity?
you can check validity of your access_token
```
URL: http://host:port/oauth/check_token
Method: POST

- Headers:
Authorization: Basic base64Encode(client_id:client_secret)

- Body
token: eyJhbGciOiJSUzI1NiIsIn.....
```
#### Response sample:
```
{
    "aud": [
        "AUTHENTICATION"
    ],
    "user_name": "admin",
    "scope": [
        "read",
        "write"
    ],
    "active": true,
    "exp": 1573850179,
    "userId": 1,
    "authorities": [
        "ROLE_SUPER_ADMIN"
    ],
    "jti": "7e2114f0-325b-4e54-8b72-6c2697a0a730",
    "client_id": "client"
}
```

# How get user details information?
you can get user details information by your access_token
```
URL: http://host:port/token_info
Method: GET

- Headers:
Authorization: Bearer eyJhbGciOiJSUzI1NiIsIn.....
```
#### Response sample:
```
{
    "id": 1,
    "type": "super_admin",
    "username": "admin",
    "password": null,
    "email": "admin@example.com",
    "fullName": "Hamid Atyabi",
    "companyName": null,
    "createTime": "2019-11-15T17:39:46.000+0000",
    "credit": 0.0,
    "status": true
}
```
