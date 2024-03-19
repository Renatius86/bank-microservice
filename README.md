# Банковский микросервис

## Описание
Этот микросервис предназначен для управления транзакциями и лимитами расходов в банковской системе.

## Требования
- Java 11 или выше
- Maven
- PostgreSQL

## Установка и запуск
1. Склонируйте репозиторий:

    ```bash
    git clone https://github.com/your/repository.git
    cd repository
    ```

2. Создайте базу данных PostgreSQL:

    ```sql
    CREATE DATABASE bank_microservice;
    ```

3. Укажите параметры подключения к базе данных в файле `application.properties`:

    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/bank_microservice
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

4. Запустите приложение с помощью Maven:

    ```bash
    mvn spring-boot:run
    ```

5. После успешного запуска, вы можете открыть страницу Swagger, перейдя по URL-адресу `http://localhost:8080/swagger-ui/`.

## Использование
- Документация API доступна на странице Swagger.
- Вы можете использовать API для создания транзакций, установки лимитов и получения списка транзакций, превысивших лимит.