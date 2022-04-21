package com.embedica.car_directory;

/**
 * TODO global: Универсальны метод GET с параметрами
 * TODO global: Универсальны метод GET с параметрами + Сортировка
 * TODO global: Универсальны метод GET с параметрами + Пагинация

 * TODO global: метод controller.save выглядит как жопа.
 * TODO global: метод controller.delete выглядит как жопа.
 * TODO global: все id обычно это тип Long(именно Object, что бы можно быть использовать null)
 * TODO global: docker || in memory DB
 * TODO global: Расширить статистику и отдавать Object

 *
 * * 2 - по возможности сортировка && фильтрация не должна быть в коде, когда она может быть сделана БАЗОЙ - База под эти операции Специально заточена, она специализируется на этом - findAllCardSortedById

 * 4 - Обычный подход(кажется он был в курсе когда-то, но это не точно), если тебе на endpoint приходит сущность с
 * id=null это Create Entity
 * id!=null это Update Entity
 * нету проверки при save что id != 0
 * id == 0 - Новая Тачка
 * id != 0 - Обновляем Тачку
 * У тебя будет вафля, если кто-то, пришёл запрос с id который Уже есть в базе. У тебя он Перезатрёт, то что было. Хотя, хотел просто добавить.
 *
 * 9 - В целом видна проблема что ты выносишь Логику в Controller, а её наоборот, нужно тащить в Service! Код упростился бы, в раза 2.
 * 10 - если подумать, то endpoint'ы в стиле findAllBy... можно с хлопнуть в 1 Удобный метод с кучей Опциональных параметров.
 * GET /car/findAll?id=...&number=...&mark=...&color=...&year=...
 * + можно Централизованно сделать отдельные валидации на каждый параметр
 * 11 - делай сам ревью кода перед сдачей, но только с Чистым взглядом, а то Сразу после - взгляд Замыленный и ВСЁ видится Идеальным.
 
 * 13 - хороший подход(у меня и у Макса на работах юзается) когда к сущности добавляется 2 технических поля.
 * createdAt : LocaleDateTime
 * updatedAt : LocaleDateTime
 * * с учётом, что предполагается логика работы с этими полями. Сейчас это именно тот самый случай. (getEarliest, getLasest)
 *
 * 15 - url реально на REST не похожи тут стоит их переписать. + Вопрос, дай точное/своё определение что такое REST API.
 * 15.1 - база в url всегда в Единственном числе.
 * 15.2 - не понятные Слова-символы в url
 *
 * 16 - сделай пожалуйста для нас IN memory DB, типа h2 db ИЛИ docker штуку, а то хочется запустить, но не получается из-за отсутствия базы.
 * https://www.baeldung.com/java-in-memory-databases
 *
 * 17 - есть интересная задача на новый endpoint. Сделай пагинацию, пока просто почитай про это, потом опишу точности
 *
 *
 *
 * DONE
 * TODO: 1 - CrudRepository -> JpaRepository - Было бы, больше Удобного API для тебя. findAll() отдаёт не Iterable, а удобный тебе List или даже Stream
 * TODO: * 3 - Spring Умный, ты exception можешь кидать откуда Угодно и оно будет работать.
 *  * все Exception можно Выкидывать в Service или где-то, ещё, нету Обязаности Тащить логику в contoller - значительная часть кода, могла бы просто схлопнутся, при этом логика бы НЕ пострадала бы.
 *  *  throw new ResponseStatusException(
 *  *                     HttpStatus.BAD_REQUEST,
 *  *                     "The object is already exist!!!."
 *  *             );
 *  * её Можно и Логично выкидывать её в Service классе
 * TODO: 5 - carService.dateOfLastEntry - можно сделать чисто в 1 строку в carRepository
 * TODO:  * 7 - Принимать в endpoint тот же, класс что и Entity в БД, Опасно! Это позволяет реально выстрелить себе в ногу.(позже напомни мне, найти в переписки с моим ТЛ, аргументы для этой точки зрения.)
 *  * Лучше делать отдельными классами DTO что приходит и Entity - CarSaveDTO && Car + все аннотации на валидацию вынесешь в DTO
 * TODO:  * 8 - идея с repository.count() - мне сначала нравилась, но чем дальше, тем меньше. + хард-код findById(1)
 *  * что будет если мы:
 *  * - удалим сущность с id=1?
 *  * - сделаем 190 машин, потом Почистим 50 не актуальных(например из середины списка) и сделаем GET /car/lastDate - ? Что оно вернёт? Отработает ли, это корректно?
 *  * - так же с GET /car/firstDate
 * TODO:  * 6 - не по REST подходу @DeleteMapping("/rmv/{id}")
 *  * Для REST норм просто Базовый url сущности + id + HTTP DELETE
 *  * DELETE /car/rmv/102 - НЕТ
 *  * DELETE /car/102 - ДА
 * TODO: * 12 - просто у Spring JPA есть Гибкий способ по Названию методов в repository создавать sql query который тебе нужен - этого тут мы не увидели.
 * TODO: * 14 - delete -
 *  * ResponseStatusException - тут не очень смотреться, лучше заменить на свой Exception + ResponseStatus
 *  * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 *  * google: spring exceptionhandler
 *  * * об этом пункте мы можем поговорить отдельно голосом, но anyway сначала глянь сам.
 * TODO global: Нету соответствия REST в url
 *  1 - база url должна быть в Единственном числе, а не во множественном. /cars -> /car
 *  2 - GET /car/all -> GET /car
 *  2 - POST /car/income -> POST /car
 * TODO:
 * TODO:
 * TODO:
 * TODO:
 */
public interface Todo {
}
