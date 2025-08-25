# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew :eureka-server:bootJar --no-daemon

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/eureka-server/build/libs/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/app.jar"]
