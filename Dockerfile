FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# ADD THIS LINE (fix)
RUN chmod +x mvnw

# build project
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/resumeanalyzer-0.0.1-SNAPSHOT.jar"]