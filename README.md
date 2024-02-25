./mvnw clean install

./mvnw spring-boot:run

через постман нужно будет посылать put запрос на

http://localhost:8080/api/temperature/1

в теле запроса будет

{
"temperature": 50
}

значение "temperature" можно изменить на желаемую температуру
