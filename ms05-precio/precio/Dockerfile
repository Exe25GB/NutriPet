FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT [ "java", "-jar", "app.jar" ]
