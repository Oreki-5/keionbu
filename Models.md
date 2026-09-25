# Users models

### User creation request:

```json
{
  "username": "asd",
  "password": "asd",
  "firstName": "Oreki",
  "lastName": "Houtarou",
  "subject": "Guitar",
  "email": "asd@asd.com"
}
```

### User creation reponse:

```json
{
  "createdAt": "2026-09-25T09:45:18.650767900Z",
  "email": "asd@asd.com",
  "firstName": "Oreki",
  "id": "456uygf",
  "lastName": "Houtarou",
  "role": "ROLE_TEACHER",
  "subject": "Guitar",
  "token": null,
  "updatedAt": "2026-09-25T09:45:17.222706700Z",
  "username": "oreki5"
}
```

### User update request:

```json
{
  "username": "ritsu12",
  "firstName": "Ritsu",
  "lastName": "Tanaka",
  "subject": "Drumset"
}
```

### User update reponse:

```json
{
  "createdAt": "2026-09-25T09:45:18.650767900Z",
  "email": "asd@asd.com",
  "firstName": "Oreki",
  "id": "4354657yt",
  "lastName": "Houtarou",
  "role": "ROLE_TEACHER",
  "subject": "Guitar",
  "token": null,
  "updatedAt": "2026-09-25T09:45:17.222706700Z",
  "username": "oreki5"
}
```

### User login request:

```json
{
  "username": "ritsu12",
  "password": "asfd"
}
```

### User login reponse:

```json
{
  "createdAt": "2026-09-25T09:45:18.650Z",
  "email": "asd@asd.com",
  "firstName": "Oreki",
  "id": "35465tr",
  "lastName": "Houtarou",
  "role": "ROLE_TEACHER",
  "subject": null,
  "token": "eyJhbGciOiJIUzUxM",
  "updatedAt": "2026-09-25T09:45:17.222Z",
  "username": "oreki5"
}
```

# Lessons models

### Lesson creation request:

```json
request param : "request"
{
  "lessonNo": 8,
  "lessonName": "guitar 8",
  "lessonDesc": "simple exercise 8",
  "objectives": [
    "to get started","understand the basics"
  ],
  "lessonDifficulty": "hard",
  "requiredScore": 0
}

request param : "lessonFile" (application/octet-stream)
```

### Lesson creation reponse:

```json
{
  "createdAt": "2026-09-25T10:01:38.759933400Z",
  "id": "wvetbreyrtyj",
  "lessonDesc": "simple exercise 8",
  "lessonDifficulty": "hard",
  "lessonFile": "Exercise_8.png",
  "lessonName": "guitar 8",
  "lessonNo": 8,
  "objectives": ["to get started", "understand the basics"],
  "requiredScore": 0,
  "teacherId": "2f534354",
  "teacherName": "oreki5",
  "updatedAt": "2026-09-25T10:01:37.787434Z"
}
```

# Assignment models

### Assignment list view:

```json
{
    "approvalStatus": string,
    "id": string,
    "lesson": {
        "id": string,
        "lessonName": string,
        "lessonNo": int
    },
    "student": {
        "id": string,
        "username": string
    },
    "teacher": {
        "id": string,
        "username": string
    }
}
```

### Assignment list view:

```json
{
    "approvalStatus": string,
    "id": string,
    "lesson": {
        "id": string,
        "lessonName": string,
        "lessonNo": int
    },
    "student": {
        "id": string,
        "username": string
    },
    "teacher": {
        "id": string,
        "username": string
    }
}
```

### Join Teacher Request

```json
{
  "id": "sfdfhgtj4356",
  "teacherId": "wadsfgtre456"
}
```


### Join Teacher Response

```json
{
    "firstName": "Rin",
    "id": "3453thg",
    "lastName": "Shima",
    "teachersList": [
        {
            "createdAt": "2026-09-25T09:45:18.650Z",
            "email": "asd@asd.com",
            "firstName": "Oreki",
            "id": "rre46trre",
            "lastName": "Houtarou",
            "role": "ROLE_TEACHER",
            "subject": "Guitar",
            "token": null,
            "updatedAt": "2026-09-25T10:09:13.760775600Z",
            "username": "oreki5"
        }
    ]
}
```
