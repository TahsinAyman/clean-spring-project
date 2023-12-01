# Student API

### Description

This is a simple API that allows you to create, read, update and delete students from a database. It is built with Spring Boot and uses a MySQL database. for testing environment, it uses H2 database. this project uses design principles in a good manner. it is a good example of how to use Spring Boot and Spring Data JPA.

### Requirements

- Java
- Maven
- MySQL
- Postman

### API Documentation

#### Fetch All Student

```http request
GET /api/students/
```

#### Fetch Single Student

```http request
GET /api/students/{id}
```

#### Create Student

```http request
POST /api/students/
Content-Type: application/json

{
    "name": "John Doe",
    "class": 12,
    "roll": 1,
    "section": "A"
}
```

#### Update Student

```http request
PUT /api/students/{id}

{
    "name": "John Doe",
    "class": 12,
    "roll": 2,
    "section": "A"
}
```

#### Delete Student

```http request
DELETE /api/students/{id}
```

### Database Schema

| Field Name | Data Type         | Constraints                 |
|------------|-------------------|-----------------------------|
| id         | Long              | Primary Key, Auto-generated |
| name       | String            | Not Null                    |
| class      | Integer           | Not Null                    |
| roll       | Integer           | Not Null, Unique            |
| section    | String (length 1) | Not Null                    |

