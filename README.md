# “Справочник автомобилей”
____________________

   Реализовать сервис справочника автомобилей с хранением
данных в базе или файле.

Минимальная информация по объекту:

 Номер (регистрационный знак, например АА999А96);
- Марка;
- Цвет;
- Год выпуска;

![Image of Arch](/Screenshot_2.jpg)

Used technologies
______________________________________________
- Java Core
- Rest API
- Spring Boot Data JPA
- Maven
- PostgreSQL (DB)  
- Travis C.I.

Кратко о методах:
- /all
Получить список всех сущностей хранимых в БД
  ![Image of Arch](/Screenshot_2.jpg)
 
- /income
Добавление автомобиля(сущности) в БД
  
  ![Image of Arch](/Screenshot_2.jpg)


- /fndByClr/{color}
Получение списка сущностей из БД по указанному параметру(цвет)
  
![Image of Arch](/Screenshot_2.jpg)


- /fndByClrAndYear
  Получение списка сущностей из БД используя параметры(год, цвет)
  
  ![Image of Arch](/Screenshot_2.jpg)


- /fndByMTYear/{year}
  Получение списка сущностей из БД используя параметр (moreThan-больше чем)
  
  ![Image of Arch](/Screenshot_2.jpg)


- /ordByYear
  Получение сущностей из БД отсортированных по годам в порядке возрастания
  
  ![Image of Arch](/Screenshot_2.jpg)

 
-  /fndByClrAndYear
  найти по году и цвету, 
  пример запроса /cars/fndByClrAndYear?year=2008&color=Red
   
  ![Image of Arch](/Screenshot_2.jpg)


- /lastDate
Дата последней записанной сущности
  
  ![Image of Arch](/Screenshot_2.jpg)


- /firstDate
Дата первой записанной сущности в БД
  
![Image of Arch](/Screenshot_2.jpg)


- /rmv/{id}
Удаление хранимой сущности из БД по ее id
  
![Image of Arch](/Screenshot_2.jpg)

  
- /stcCount
Статистика от БД, общее количество хранимых на данный момент сущностей
  
![Image of Arch](/Screenshot_2.jpg)
  




