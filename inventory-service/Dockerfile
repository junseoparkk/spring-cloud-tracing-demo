# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew :inventory-service:bootJar --no-daemon

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/inventory-service/build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]
