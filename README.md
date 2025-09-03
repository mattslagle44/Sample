# Todo Sample (Spring Boot)

Minimal REST API demonstrating:

- Clean controller design  
- DTO validation (Jakarta Validation)  
- Unit tests with MockMvc  

---

## Why this project?

This repository was created as a code sample for job applications.  
It shows core back-end development practices:

- API design (REST endpoints for managing Todos)  
- Input validation and error handling  
- Automated tests to ensure reliability  

---

## Run

```bash
./mvnw spring-boot:run
```

Once running, the API will be available at:  
[http://localhost:8080/api/todos](http://localhost:8080/api/todos)

---

## Try it out

Once the server is running, you can interact with the API using curl, Postman, or any HTTP client.

### 1. List all Todos
```bash
curl http://localhost:8080/api/todos
```
Response:
```json
[]
```

### 2. Create a new Todo
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{ "title": "Write cover letter", "notes": "Keep it simple", "done": false }'
```
Response:
```json
{
  "id": 1,
  "title": "Write cover letter",
  "notes": "Keep it simple",
  "done": false
}
```

### 3. List again
```bash
curl http://localhost:8080/api/todos
```
Response:
```json
[
  {
    "id": 1,
    "title": "Write cover letter",
    "notes": "Keep it simple",
    "done": false
  }
]
```

### 4. Validation example
Try creating a Todo with an empty title:
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{ "title": "", "notes": "missing title", "done": false }'
```
Response (400 Bad Request):
```json
{
  "errors": [
    {
      "field": "title",
      "message": "must not be blank"
    }
  ]
}
```

---

## Test

```bash
./mvnw test
```

This runs the automated unit tests (JUnit + MockMvc) that verify the API behavior.

---

## Tech Stack

- Java 17  
- Spring Boot 3  
- Maven (with Maven Wrapper for portability)  
- JUnit 5 + MockMvc  
