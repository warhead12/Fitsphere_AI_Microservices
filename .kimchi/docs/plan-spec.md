# Commenting Plan: Fitness Microservice Project

## Goal
Add explanatory comments to every Java source file in the fitness microservice project so that anyone with very little Spring Boot experience can read the comments and understand what each code block does. No code changes — only comments.

Also create a README.md at the project root explaining all Spring annotations, frameworks, and technologies used across the 4 services.

## Files to comment (source .java files only — 31 files)

### Chunk 1: Eureka Service (3 files)
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/eureka/src/main/java/com/server/eureka/EurekaApplication.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/eureka/src/test/java/com/server/eureka/EurekaApplicationTests.java`

### Chunk 2: User Service (8 files)
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/UserserviceApplication.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/service/UserService.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/controller/UserController.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/dto/RegisterRequest.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/dto/UserResponse.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/model/User.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/model/UserRole.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/main/java/com/fitness/userservice/repository/UserRepository.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/userservice/src/test/java/com/fitness/userservice/UserserviceApplicationTests.java`

### Chunk 3: Activity Service (16 files)
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/ActivityserviceApplication.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/ActivityRepository.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/controller/ActivityController.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/service/ActivityService.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/service/UserValidationService.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/dto/ActivityResponse.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/dto/ActivityRequest.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/config/RabbitMqConfig.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/config/MongoConfig.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/config/WebClientConfig.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/model/Activity.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/main/java/com/fitness/activityservice/model/ActivityType.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/activityservice/src/test/java/com/fitness/activityservice/ActivityserviceApplicationTests.java`

### Chunk 4: AI Service (13 files)
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/AiserviceApplication.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/controller/RecommendationController.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/config/MongoConfig.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/config/RabbitMqConfig.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/repository/RecommendationRepository.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/service/ActivityMessageListener.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/service/RecommendationService.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/model/Recommendation.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/main/java/com/fitness/aiservice/model/Activity.java`
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/aiservice/src/test/java/com/fitness/aiservice/AiserviceApplicationTests.java`

### Chunk 5: README.md
- `/mnt/c/Users/Abhishek Anand/Desktop/fitness_microservice/README.md`

## Comment Style
- Use `//` line comments (not block comments `/* */`) for consistency with modern Spring Boot conventions
- Add a header comment at the top of each file explaining: what the class does, what Spring annotation it uses, and what its purpose is in the service
- Add inline comments on:
  - Every `@` annotation explaining what it does
  - Every method explaining parameters, return type, and logic
  - Any non-obvious code (business logic, error handling, async calls)
- Keep comments concise (1-3 lines per comment block)
- Do NOT modify any existing code — only add comments