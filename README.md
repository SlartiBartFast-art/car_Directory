# “Справочник автомобилей”

[![Build Status](https://app.travis-ci.com/SlartiBartFast-art/car_Directory.svg?branch=master)](https://app.travis-ci.com/SlartiBartFast-art/car_Directory)
____________________

   Реализовать сервис справочника автомобилей с хранением
данных в базе или файле.

Минимальная информация по объекту:

 Номер (регистрационный знак, например АА999А96);
- Марка;
- Цвет;
- Год выпуска;

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_1.jpg)

Дополнительные необязательные условия (будут плюсом):
- Сортировка по атрибутам при запросе списка;
- Дополнительные атрибуты объектов;
- API можно усложнять и менять, при условии соответствия минимальным
требованиям

Used technologies
______________________________________________
- Java Core
- Rest API
- Spring Boot (Data JPA, Web)
- Maven
- PostgreSQL (DB) 
- Docker
- Postman
- Travis C.I.


Кратко о методах:

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_2.jpg)

- /
Получить список всех сущностей хранимых в БД
  
  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_3.jpg)
 
- /
Добавление автомобиля(сущности) в БД
  
  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_4.jpg)

after

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_5.jpg)

валидация

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_14.jpg)

- /fndByClr/{color}
Получение списка сущностей из БД по указанному параметру(цвет)
  
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_8.jpg)


- /fndByClrAndYear
  Получение списка сущностей из БД используя параметры(год, цвет)
  
  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_9.jpg)

валидация
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_17.jpg)

- /fndByMTYear/{year}
  Получение списка сущностей из БД используя параметр (moreThan-больше чем)
  
  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_10.jpg)

валидация
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_15.jpg)

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_16.jpg)

- /ordByYear
  Получение сущностей из БД отсортированных по годам в порядке возрастания
  
  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_11.jpg)

 
- /lastDate
Дата последней записанной сущности
  
  ![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_12.jpg)


- /firstDate
Дата первой записанной сущности в БД

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_13.jpg)


- /{id}
  Удаление хранимой сущности из БД по ее id
  
добавим:

![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_18.jpg)

удалим:  
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_6.jpg)

  
- /statistics
Статистика от БД,
  
общая информация(
  количество хранимых на данный момент сущностей,
  дата первой записи в БД, 
  дата последней записи в БД)
  
![Image of Arch](https://github.com/SlartiBartFast-art/car_Directory/blob/master/image/Screenshot_7.jpg)
  




