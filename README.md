# “Справочник автомобилей”

[![Build Status](https://app.travis-ci.com/SlartiBartFast-art/car_Directory.svg?branch=master)](https://app.travis-ci.com/SlartiBartFast-art/car_Directory)
![GitHub top language](https://img.shields.io/github/languages/top/SlartiBartFast-art/car_Directory?logo=java&logoColor=red)
![GitHub last commit](https://img.shields.io/github/last-commit/SlartiBartFast-art/car_Directory?logo=github)

____________________

Реализовать сервис справочника автомобилей с хранением данных в базе данных.

Минимальная информация по объекту:

Номер (регистрационный знак, например АА999А96);

- Марка;
- Цвет;
- Год выпуска;

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_1.jpg)

Дополнительные необязательные условия (будут плюсом):

- Сортировка по атрибутам при запросе списка;
- Дополнительные атрибуты объектов;
- API можно усложнять и менять, при условии соответствия минимальным требованиям

Used technologies
______________________________________________

- Java Core
- Rest API
- Spring Boot (Data JPA, Web)
- Maven - Build Tool
- PostgreSQL (DB)
- Docker
- Postman - Test REST API
- Travis C.I.

Docker образ приложения, Вы можете скачать по ссылке:

https://hub.docker.com/repository/docker/slartibartfastart/car-repository

или To pull image from repository:

docker pull slartibartfastart/car-repository:latest

Краткая инструкция по использованию docker-compose
создайте на вашем компьютере рабочую папку, расположение и имя выбираете сами
для Windows: C:\projects\car_repository 
для Linux:~coding/docker/car_repository
скачайте файл docker-compose.yml, и положите в созданную вами папку
https://github.com/SlartiBartFast-art/car_Directory/blob/deploy/src/main/docker/docker-compose.yml
откройте окно терминала
для Windows в текущей папке в строке пути введи cmd, нажмите Enter
для Linux откройте окно терминала, и перейдите в созданную вами папку cd имя_папки

Запустим: docker-compose up

В вашем браузере создайте новое окно и введите - http://localhost:8080/car/
далее вы можете пользоваться согласно имеющегося функционала ниже

Завершим работу: docker-compose down

Кратко о методах: 

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_5.jpg)

- / Получить список всех сущностей хранимых в БД

  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_3.jpg)

- / Добавление автомобиля(сущности) в БД

  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_4.jpg)

after

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_6.jpg)

валидация

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_7.jpg)

- /findByColor/{color} Получение списка сущностей из БД по указанному параметру(цвет)

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_8.jpg)

- /findByYearAndColor Получение списка сущностей из БД используя параметры(год, цвет)

  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_9.jpg)

валидация
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_17.jpg)

- /findByMoreThanYear/{year} Получение списка сущностей из БД используя параметр (moreThan-больше чем)

  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_10.jpg)

валидация
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_18.jpg)

- /ordByYear Получение сущностей из БД отсортированных по годам в порядке возрастания

  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_11.jpg)

- /firstDate Дата первой записанной сущности в БД

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_12.jpg)

- /lastDate Дата последней записанной сущности

  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_13.jpg)

- /{id} Удаление хранимой сущности из БД по ее id

добавим:

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_14.jpg)

удалим:
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_15.jpg)

- /statistics Статистика от БД,

общая информация:
(количество хранимых на данный момент сущностей, дата первой записи в БД, дата последней записи в БД)

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_16.jpg)

так же, доступен вывод страниц согласно условиям пагинации и сортировки

http://localhost:8080/car/cars?pageSize=5

http://localhost:8080/car/cars?pageSize=5&pageNo=1

http://localhost:8080/car/cars?pageSize=5&pageNo=2

http://localhost:8080/car/cars?pageSize=5&pageNo=1&sortBy=id

http://localhost:8080/car/cars?pageSize=5&pageNo=1&sortBy=id&sortDir=asc

далее, скриншоты некоторых примеров работы

http://localhost:8080/car/cars?pageSize=3&pageNo=1

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/deploy/image/Screenshot_21.jpg)

http://localhost:8080/car/cars?pageSize=2&pageNo=1&sortBy=id&sortDir=asc

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/deploy/image/Screenshot_20.jpg)

/user/?search=

Простой пользовательский запрос
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_19.jpg)

Сложный пользовательский запрос, с произвольной комбинацией
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_17.jpg)

В папке проекта collections Вы сможете найти готовую коллекцию запросов для работы в программе Postman 
  




