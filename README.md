# Fitsphere_AI Microservice

A **microservices-based fitness tracking application** built with **Spring Boot**, **Spring Cloud**, and a suite of supporting frameworks. This project demonstrates a real-world distributed system architecture with **4 independent services** that communicate via **REST APIs**, **message queues (RabbitMQ)**, and **service discovery (Eureka)**.
## Project Walkthrough : Behind the curtains

### User creation postman endpoint

<img width="1261" height="662" alt="image" src="https://github.com/user-attachments/assets/8f65cdf7-41da-42c2-9458-0b023c82aee2" />

### User Created in Postgress Db

<img width="1279" height="684" alt="image" src="https://github.com/user-attachments/assets/0d812501-e8fe-407f-a381-34d4ffb83212" />

### Activity creation postman endpoint

<img width="2560" height="1440" alt="Screenshot (98)" src="https://github.com/user-attachments/assets/e6c45cc9-692c-4415-87d7-b3fa42b43752" />

### Activities created in MongoDb

<img width="1021" height="641" alt="image" src="https://github.com/user-attachments/assets/562dbad5-dc30-4975-bc8c-d92a5073c902" />

### Accessing Activity service using gateway authenticated by keycloak

<img width="915" height="641" alt="image" src="https://github.com/user-attachments/assets/9d01c2bf-8868-47c3-92a2-187b2d34956d" />

### Connected Actvity Service with AI Service using RabbitMQ

<img width="1237" height="589" alt="image" src="https://github.com/user-attachments/assets/a79334ca-8194-4a9e-8c61-e7e2dc41245c" />
<img width="1277" height="407" alt="image" src="https://github.com/user-attachments/assets/c94aa57e-3657-47c7-a6da-d744a7ad62c3" />

### Recommendation stored in MongoDb Database

<img width="1038" height="643" alt="image" src="https://github.com/user-attachments/assets/1346ca0d-3158-4a49-8bc2-27ecfe358b2f" />

### Service discovery using Eureka

<img width="1260" height="566" alt="image" src="https://github.com/user-attachments/assets/75bcac50-6b9f-4c34-87b4-dc70eac833c0" />


## Project Walkthrough : User View

### Landing page

<img width="1238" height="550" alt="image" src="https://github.com/user-attachments/assets/3fef1dd5-6d7e-4e02-8a27-1a8b2b0f169c" />

### Keycloak SignIn Page

<img width="1229" height="557" alt="image" src="https://github.com/user-attachments/assets/e7a03337-65f2-40fc-a2ca-499ae6b4eb99" />

### Users listing their workout details

<img width="1250" height="541" alt="image" src="https://github.com/user-attachments/assets/b8b3d5b8-ebd7-49e7-b53c-cab7157c287f" />

### AI Generated response

<img width="1270" height="554" alt="image" src="https://github.com/user-attachments/assets/2ba34cfe-17e8-42ad-9f52-06ebd8a893a1" />
<img width="1235" height="555" alt="image" src="https://github.com/user-attachments/assets/0350ce51-d869-49be-a5e4-e7dcd643f62b" />










## Project Structure

```
fitness_microservice/
├── eureka/              # Service Discovery (Eureka Server) - Port 8761
│   └── src/main/java/com/server/eureka/
├── userservice/          # User Management - Port 8081
│   └── src/main/java/com/fitness/userservice/
├── activityservice/      # Activity Tracking - Port 8082
│   └── src/main/java/com/fitness/activityservice/
└── aiservice/            # AI Recommendations - Port 8083
    └── src/main/java/com/fitness/aiservice/
```

## How Services Communicate

```
                ┌───────────────┐
                │   Eureka       │
                │   Server       │
                │   (Port 8761)  │
                └───────┬───────┘
                        │
        ┌───────────────┼───────────────┐
        │               │               │
   ┌────▼────┐    ┌─────▼─────┐   ┌─────▼─────┐
   │  User    │    │  Activity  │   │   AI      │
   │  Service  │    │  Service   │   │  Service   │
   │(Port 8081)│    │ (Port 8082)│   │ (Port 8083)│
   └──────────┘    └─────┬─────┘   └──────────┘
                        │
                   (RabbitMQ)
                        │
                   ┌─────▼─────┐
                   │  AI Service │
                   │(via Listener)│
                   └──────────┘
```

- **All services** register with **Eureka** (service discovery)
- **Activity Service** → calls **User Service** via load-balanced `WebClient` to validate users
- **Activity Service** → publishes **activity events** to RabbitMQ → **AI Service** consumes them

---

## Spring Annotations & Frameworks Used

### 1. Spring Boot Starter Annotations
| Annotation | Where Used | What It Does |
|---|---|---|
| `@SpringBootApplication` | All 4 service `*Application.java` | **Marks the main class** of a Spring Boot application. Enables auto-configuration, component scanning, and property file loading — the **entry point** for the app |
| `@SpringBootTest` | All test files | **Bootstraps the test context** — loads the full Spring application context so tests can run with real dependencies |
| `@EnableEurekaServer` | `EurekaApplication.java` | **Turns this service into a Eureka Server** — other microservices register here so they can find each other by name |

### 2. Web Layer (`spring-boot-starter-web`)
| Annotation | Where Used | What It Does |
|---|---|---|
| `@RestController` | `ActivityController.java`, `UserController.java`, `RecommendationController.java` | **Combines `@Controller` + `@ResponseBody`** — marks the class as a REST API handler. Every method return value is automatically serialized to JSON |
| `@RequestMapping("/api/...")` | All controllers | **Defines the base URL path** for all endpoints in this controller. E.g., all methods in `ActivityController` will be under `/api/activities` |
| `@GetMapping` | Controllers | **Maps HTTP GET requests** to the annotated method. Parameters: `@PathVariable` extracts values from the URL path |
| `@PostMapping` | `ActivityController.java`, `UserController.java` | **Maps HTTP POST requests** — used for creating/registering new resources |
| `@RequestBody` | Controller method parameters | **Deserializes the HTTP request body** (JSON) into the annotated Java object |
| `@RequestHeader("X-User-ID")` | `ActivityController.java` | **Extracts a specific HTTP header** value. Here: reads a custom `X-User-ID` header for user identification |
| `@PathVariable` | All controllers | **Binds a URL template variable** (e.g., `{userId}` in `/api/users/{userId}`) to a method parameter |
| `@Valid` | `UserController.java` | **Triggers Jakarta Bean Validation** on the request body — if validation fails, returns 400 Bad Request with field errors |
| `@ResponseStatus` | (implied via `ResponseEntity`) | **Customizes the HTTP status code** of the response |

### 3. Service Layer (`@Service`)
| Annotation | Where Used | What It Does |
|---|---|---|
| `@Service` | `ActivityService.java`, `UserValidationService.java`, `UserService.java`, `RecommendationService.java`, `ActivityMessageListener.java` | **Marks this class as a business-logic service** — Spring auto-detects it and injects it into controllers or other services that need it |
| `@RequiredArgsConstructor` | `ActivityService.java`, `RecommendationController.java` | **Lombok annotation** — generates a constructor that takes all `final` fields as parameters. Enables **constructor injection** (the proper way to inject dependencies) |
| `@AllArgsConstructor` | `ActivityController.java`, `UserController.java` | **Lombok annotation** — generates a constructor taking ALL fields (not just `final` ones). Used for **field injection** via constructor |
| `@Slf4j` | Multiple services | **Lombok annotation** — injects a `log` field (SLF4J logger) so you can write `log.info()`, `log.error()`, etc. without manually creating a logger |
| `@Autowired` | `UserService.java` | **Spring's field injection** — wires the `UserRepository` bean into this field at runtime. `@RequiredArgsConstructor` is preferred over this |

### 4. Data Access Layer (MongoDB & JPA)
| Annotation | Where Used | What It Does |
|---|---|---|
| `@Repository` | `ActivityRepository.java`, `UserRepository.java`, `RecommendationRepository.java` | **Marks as a Data Access Object** — Spring Data auto-implements CRUD methods (save, find, delete) based on the interface | 
| `@Document(collection = "...")` | `Activity.java`, `Recommendation.java` | **Maps this class to a MongoDB collection** — each instance becomes a document in the named collection |
| `@Id` | `Activity.java`, `Recommendation.java`, `User.java` | **Marks the primary key field** — auto-generated String ID for MongoDB documents |
| `@Field("metrics")` | `Activity.java` | **Customizes the MongoDB field name** — stores the field as `metrics` in the database instead of the Java name |
| `@CreatedDate` | `Activity.java`, `Recommendation.java` | **Spring Data MongoDB auditing** — automatically sets this field to the current timestamp when the document is **created** |
| `@LastModifiedDate` | `Activity.java` | **Spring Data MongoDB auditing** — automatically updates this field every time the document is **modified** |
| `@EnableMongoAuditing` | `MongoConfig.java` (both services) | **Activates MongoDB auditing** — enables `@CreatedDate` and `@LastModifiedDate` to work automatically |
| `@Entity` | `User.java` | **JPA annotation** — marks this class as a **relational database entity** (table row) |
| `@Table(name = "users")` | `User.java` | **Specifies the database table name** — maps to the `users` table in PostgreSQL |
| `@Id` + `@GeneratedValue(strategy = GenerationType.UUID)` | `User.java` | **JPA primary key** — auto-generates a **UUID** for each new user |
| `@Column(unique = true, nullable = false)` | `User.java` | **JPA column constraint** — ensures the `email` column has unique values and cannot be null |
| `@Enumerated(EnumType.STRING)` | `User.java` | **Stores enum as a string** (e.g., `"USER"` or `"ADMIN"`) in the database instead of an integer |
| `@CreationTimestamp` | `User.java` | **Hibernate-specific** — sets `createdAt` to current time when the entity is first persisted |
| `@UpdateTimestamp` | `User.java` | **Hibernate-specific** — updates `updatedAt` every time the entity is saved/modified |
| `JpaRepository<User, String>` | `UserRepository.java` | **Spring Data JPA** — provides `save()`, `findById()`, `findAll()`, `delete()`, `existsById()` out of the box |

### 5. Configuration Layer (`@Configuration`)
| Annotation | Where Used | What It Does |
|---|---|---|
| `@Configuration` | `RabbitMqConfig.java`, `MongoConfig.java`, `WebClientConfig.java` (both services) | **Marks as a Spring configuration class** — defines `@Bean` methods that create and wire up infrastructure objects |
| `@Bean` | All `@Configuration` classes | **Declares a method that returns an object** to be managed by Spring's IoC container. The returned object becomes a bean that other classes can `@Autowired` or use |
| `@Value("${...}")` | `RabbitMqConfig.java`, `ActivityService.java` | **Injects a property from application.yaml** — reads values like `rabbitmq.queue.name` from the YAML config file |

### 6. WebClient & Load Balancing
| Annotation | Where Used | What It Does |
|---|---|---|
| `@LoadBalanced` | `WebClientConfig.java` | **Enables client-side load balancing** on the `WebClient.Builder`. When you call `http://USER-SERVICE/...`, Eureka will resolve the actual host:port of the User Service instance |

### 7. Spring Cloud & Eureka
| Annotation | Where Used | What It Does |
|---|---|---|
| `@EnableEurekaServer` | `EurekaApplication.java` | **Activates the Eureka Server** — other services register here so they can discover each other |
| `spring-cloud-starter-netflix-eureka-client` | All 3 services (pom.xml) | **Registers each service with Eureka** — enables them to find each other by **service name** (e.g., `user-service`, `activity-service`, `ai-service`) |

### 8. Messaging (RabbitMQ / AMQP)
| Annotation | Where Used | What It Does |
|---|---|---|
| `@RabbitListener(queues = "activity.queue")` | `ActivityMessageListener.java` | **Listens on a RabbitMQ queue** — when a message arrives on `activity.queue`, this method is called automatically with the deserialized `Activity` object |
| `RabbitTemplate` | `ActivityService.java` | **Publishes messages to RabbitMQ** — `convertAndSend(exchange, routingKey, savedActivity)` sends the activity data to the exchange |
| `DirectExchange` | `RabbitMqConfig.java` | **Defines a direct exchange** — messages are routed to queues that match the routing key exactly |
| `Queue` | `RabbitMqConfig.java` | **Defines a durable queue** — `true` = queue survives broker restarts |
| `Binding` | `RabbitMqConfig.java` | **Binds a queue to an exchange** with a routing key — the message routing rule |
| `Jackson2JsonMessageConverter` | `RabbitMqConfig.java` | **Converts Java objects to/from JSON** when sending/receiving messages via RabbitMQ |

### 9. Validation (`spring-boot-starter-validation`)
| Annotation | Where Used | What It Does |
|---|---|---|
| `@NotBlank` | `RegisterRequest.java` | **Validates that the field is not null and not empty** (whitespace-only is also invalid) |
| `@Email` | `RegisterRequest.java` | **Validates that the value is a valid email address** format |
| `@Size(min = 6)` | `RegisterRequest.java` | **Validates that the string length is at least 6** characters |

---

## Frameworks & Dependencies (POM-level)

### What each Maven/Gradle dependency provides:

| Dependency | Service(s) | Purpose |
|---|---|---|
| `spring-boot-starter-web` | All services | **REST API support** — includes embedded Tomcat, `@RestController`, JSON serialization (Jackson), `@RequestMapping` |
| `spring-boot-starter-data-mongodb` | `activityservice`, `aiservice` | **MongoDB database access** — `MongoRepository`, `@Document`, `@Id`, `MongoTemplate` |
| `spring-boot-starter-data-mongodb-reactive` | `aiservice` | **Reactive MongoDB** (non-blocking) — `ReactiveMongoRepository`, `Mono`/`Flux` |
| `spring-boot-starter-data-jpa` | `userservice` | **JPA / Hibernate ORM** — `@Entity`, `JpaRepository`, transaction management |
| `spring-boot-starter-webflux` | `activityservice` | **Reactive WebClient** — asynchronous HTTP calls for cross-service communication |
| `spring-boot-starter-amqp` | `activityservice`, `aiservice` | **RabbitMQ support** — publish/subscribe via `RabbitTemplate`, `@RabbitListener` |
| `spring-boot-starter-validation` | `userservice` | **Jakarta Bean Validation** — `@Valid`, `@NotBlank`, `@Email`, `@Size` |
| `spring-cloud-starter-netflix-eureka-client` | All 3 business services | **Eureka service discovery** — automatic registration & lookup |
| `spring-cloud-starter-netflix-eureka-server` | `eureka` only | **Eureka server** — hosts the registry that all services talk to |
| `postgresql` | `userservice` | **PostgreSQL JDBC driver** — database connection for Postgres |
| `lombok` | All services | **Boilerplate code reduction** — `@Data`, `@Builder`, `@AllArgsConstructor`, `@Slf4j`, etc. |

### Important: Spring Boot Version Note
- **Activity Service**: Spring Boot `3.4.3`
- **User Service**: Spring Boot `3.4.2`
- **Eureka**: Spring Boot `3.4.3`
- **AI Service**: POM has `4.1.0` (which does **not exist** as a public Spring Boot release) — this is an error; should be `3.4.x`

All services use **Java 17**.

---

## Service Descriptions

### Eureka Server (Port 8761)
A **standalone discovery server** — all microservices register here. Services find each other by **name** (e.g., `http://USER-SERVICE`, `http://ACTIVITY-SERVICE`) instead of hardcoding IP addresses.

### User Service (Port 8081)
**Manages users** — registration, profile lookup, and user validation.
- **Database**: PostgreSQL via JPA/Hibernate
- **Exposes**: `/api/users/register`, `/api/users/{id}/validate`, `/api/users/{id}`
- **Key classes**:
  - `User.java`: JPA entity mapping to `users` table
  - `UserRepository.java`: Spring Data JPA repository
  - `UserController.java`: REST endpoints
  - `UserService.java`: Business logic (register, profile, validation)

### Activity Service (Port 8082)
**Tracks fitness activities** (running, swimming, yoga, etc.).
- **Database**: MongoDB (document store)
- **Messaging**: RabbitMQ (publishes activity events for AI processing)
- **Cross-service**: Validates users by calling User Service via `WebClient`
- **Key classes**:
  - `Activity.java`: MongoDB document — `@Document(collection="activities")`
  - `ActivityRepository.java`: Spring Data MongoDB repository
  - `ActivityController.java`: REST API at `/api/activities`
  - `ActivityService.java`: Constructs activity → validates user → saves to Mongo → publishes to RabbitMQ → returns response
  - `UserValidationService.java`: Calls User Service's validate endpoint via load-balanced WebClient
  - `RabbitMqConfig.java`: Declares exchange, queue, binding for activity events
  - `WebClientConfig.java`: Creates load-balanced WebClient pointing at User Service

### AI Service (Port 8083)
**Provides AI fitness recommendations** based on tracked activities.
- **Database**: MongoDB (recommendations collection)
- **Consumes**: Activity messages from RabbitMQ
- **Key classes**:
  - `Recommendation.java`: MongoDB document — contains recommendation text, improvement/suggestion lists
  - `RecommendationController.java`: Endpoints at `/api/recommendations`
  - `ActivityMessageListener.java`: Listens on RabbitMQ `activity.queue` for new activities (currently just logs them — a stub)
  - `RecommendationService.java`: Fetches recommendations by user/activity

---

## Data Flow (End-to-End Example)

1. **User registers** → `POST /api/users/register` → saved in **PostgreSQL** (`users` table)
2. **User tracks an activity** → `POST /api/activities` (with `X-User-ID` header + activity JSON body)
3. **Activity Service**:
   - Takes `X-User-ID` from the header
   - Calls **User Service** (`GET /api/users/{userId}/validate`) via **Eureka load-balanced WebClient** to verify the user exists
   - If invalid → throws error
   - If valid → **saves activity to MongoDB**
   - **Publishes** the activity to **RabbitMQ** (`fitness.exchange` → `activity.queue`)
4. **AI Service** (listens on `activity.queue`):
   - Receives the activity message
   - Currently just logs it (future: would generate recommendations and save to MongoDB)
5. **User fetches recommendations** → `GET /api/recommendations/user/{userId}`
   - Queries MongoDB `recommendations` collection by userId

---

## How to Run

```bash
# 1. Start Eureka Server first
cd eureka
mvn spring-boot:run

# 2. Start User Service
cd userservice
mvn spring-boot:run

# 3. Start Activity Service
cd activityservice
mvn spring-boot:run

# 4. Start AI Service
cd aiservice
mvn spring-boot:run
```

**Prerequisites:**
- Java 17
- Maven
- PostgreSQL running on `localhost:5432`
- MongoDB running on `localhost:27017`
- RabbitMQ running on `localhost:5672`
