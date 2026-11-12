# ORM Academy

Проект: платформа для онлайн-курсов по Hibernate/JPA 

## Стек
Java 21, Spring Boot 3.2.5, Spring Data JPA, PostgreSQL, Docker.

## Как запустить
Самый простой способ - через Docker (база поднимется на порту 5433):

docker-compose up -d db
После этого запустите класс OrmAcademyApplication в IntelliJ.
API будет доступно по адресу: http://localhost:8081/api/courses

(Если запускаете локально без Docker, проверьте настройки подключения в application.yml).

Что реализовано
20 JPA-сущностей со всеми связями (1-1, 1-M, M-M).

Репозитории Spring Data JPA.

REST API: создание курсов, запись студентов, сдача домашек, прохождение тестов.

Глобальный обработчик ошибок (GlobalErrorHandler).

15 интеграционных тестов (включая проверку LazyInitializationException).

Эндпоинты (основные)
GET /api/courses - список курсов.

POST /api/courses?teacherId=1 - создать курс.

POST /api/courses/{courseId}/enroll/{studentId} - записать студента.

POST /api/homework/submit - сдать домашку.

POST /api/quizzes/{quizId}/pass/{studentId} - пройти тест.

Известные проблемы (что не успел)
Проверку на дубликаты сделал через findAll() в цикле. Понимаю, что это медленно (O(N)), в будущем переделаю на existsBy

Пагинации нет, выводит сразу все записи.

С какими проблемами столкнулся в процессе
Долго не мог подключиться к базе, пока не поменял порт на 5433 (чтобы не конфликтовать с локальным Postgres).

Ловил StackOverflowError при выводе JSON из-за рекурсии сущностей, решил через @JsonIgnore.