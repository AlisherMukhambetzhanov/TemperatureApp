./mvnw clean install

./mvnw spring-boot:run

http://localhost:8080

через постман нужно будет посылать put запрос на

http://localhost:8080/api/temperature/1

в теле запроса будет

{
"temperature": 50
}

значение "temperature" можно изменить на желаемую температуру

![image](https://github.com/AlisherMukhambetzhanov/TemperatureApp/assets/53463211/b051c37e-d10d-4542-bc05-1912a5dedbff)

