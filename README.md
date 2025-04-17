## H2 database REST API Implementation
This is a sample h2 database implementation in a REST API format utilizing the MVC Model to control a fictitious library.

The API has been created using the Spring Boot framework with Maven for dependency tracking, and for testing purposes it serves on http://localhost:9090/

### Endpoints

As H2 is an in-memory database, the first step in instantiating the db would be to add a sample book using the /addBook endpoint. I recommend testing your instance with Postman 

| METHOD        | Body Requirements  | Endpoint                                  |
| :-----------: |:------------------:| :----------------------------------------:|
| GET           | none               | http://localhost:9090/getAllBooks         |
| GET           | "id"               | http://localhost:9090/getBookById/{id}    |
| POST          | "title", "author"  | http://localhost:9090/addBook             |
| UPDATE        | "id"               | http://localhost:9090/updateBookById/{id} |
| DELETE        | "title", "author"  | http://localhost:9090/deleteBookById/{id} |

