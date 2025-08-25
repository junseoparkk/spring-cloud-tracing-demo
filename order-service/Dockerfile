# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew :order-service:bootJar --no-daemon

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/order-service/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
