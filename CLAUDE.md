# authentication

Сервис аутентификации/регистрации пользователей в составе проекта booking.

## Стек
- Java 25, Spring Boot 4.1 (webmvc, validation, data-jpa)
- PostgreSQL 17 через Docker Compose (`compose.yaml`), поднимается автоматически через `spring-boot-docker-compose`
- Gradle (wrapper), JUnit 5 + Mockito

## Команды (Windows / PowerShell)
- Сборка: `.\gradlew.bat build`
- Тесты: `.\gradlew.bat test`
- Запуск: `.\gradlew.bat bootRun` (нужен запущенный Docker)

## Структура (`src/main/java/authentication`)
- `domain/` — JPA-сущности (`User`, таблица `users`) и enum `UserRole`
- `registration/` — регистрация: контроллер, сервис, репозиторий, DTO, `Mapper`, исключения
- `web/` — `GlobalExceptionHandler` (`@ControllerAdvice`) и `ErrorMessageDto`

## API
- `POST /create` — тело `{ "username", "password" }`, ответ 201 + `UserResponseDto`
  - 400 — невалидные поля, 409 — username занят

## Соглашения
- Внедрение зависимостей через конструктор
- Маппинг DTO ↔ сущность — через `Mapper`
- Ошибки — через кастомные исключения + обработчик в `GlobalExceptionHandler`, ответ `ErrorMessageDto`

## Правила работы с Claude
- Отвечать только на русском
- НЕ писать код за пользователя и не править исходники — пользователь учится и пишет сам.
  Только объяснять: что не так, почему, как это работает, в каком направлении двигаться.
  Небольшие иллюстрации синтаксиса/подхода допустимы, готовые решения в файлы — нет.
