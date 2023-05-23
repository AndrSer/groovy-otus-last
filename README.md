Post Manager Application

v.1.0.0

Это консольное REST --- приложение, позволяющее создавать,
редактировать, удалять авторов и их посты. Управление двумя сущностями
Author и Post в базе данных. Взаимодействие осуществляется путем
отправки HTTP --- REST запросов GET, POST, PUT, DELETE).

Компоненты приложения:

-   Spring Boot Framework 3.0.7

-   Spring Data JPA 3.0.7

-   Hibernate 6.0.0

-   FlyWay Core 9.18

-   Postgresql JDBC Driver 42.6.0

-   Spring Doc OpenApi 2.1.0

-   Bmuschko Docker Spring Boot Application Plugin 9.3.1

Система сборки --- Gradle 8.0

Java --- openjdk-17.0.7

Для работы приложения необходима инсталляция СУБД postgreSQL. На своем
примере был использован контейнер данной СУБД в docker:

https://hub.docker.com/\_/postgres

Конфигурация приложения осуществляется через application.properties

**Перед запуском приложения необходимо создать пустую БД с наименованием
«news»!** Управление миграциями осуществляет компонент FlyWay.
Определено три файла миграции БД в каталоге:

/IdeaProjects/groovy-otus-last/spring-data-demo/src/main/resources/db/migration

В случае, если готовое наполнение таблиц данными не требуется можно
исключить миграцию V00003\_\_insert_data_in_tables.sql Если же после
выполнения миграций данные были утеряны, то необходимо вычистить поле с
необходимой миграцией в таблице news.public.flyway_schema_history.

После запуска можно отправлять REST запросы. Например через postman.
Пример коллекции находится в каталоге:

/IdeaProjects/groovy-otus-last

Если присутствует необходимость, то можно запустить приложение в
контейнере docker. Для этого необходимо запустить задачу gradle:

createContainer

В результате чего будет создан контейнер с с генерированным образом и
именем

spring-data-demo

К проекту прикручен Swagger по следующему URL:

<http://localhost:8080/swagger-ui/index.html> --- если локально

<http://172.17.0.3:8080/swagger-ui/index.html> --- пример если в
контейнере

Если запуск был произведен из контейнера, то ip может быть произвольным.
