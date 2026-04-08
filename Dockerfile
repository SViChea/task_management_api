# ── Stage 1: Build ────────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy wrapper first
COPY gradlew .
COPY gradle ./gradle
RUN chmod +x gradlew           # make it executable

# Cache dependencies
COPY build.gradle settings.gradle ./
RUN ./gradlew dependencies --no-daemon

# Build
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# ── Stage 2: Run ──────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S taskmanagement && adduser -S admin -G taskmanagement

COPY --from=builder /app/build/libs/*.jar app.jar

RUN chown admin:taskmanagement app.jar

USER admin

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]