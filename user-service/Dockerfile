# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew :user-service:bootJar --no-daemon

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/user-service/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
