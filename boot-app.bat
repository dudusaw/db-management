call mvnw clean package -DskipTests
call cd docker/
call docker-compose up