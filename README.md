![Build](https://github.com/WolfOfBad/eLibraryAPI/actions/workflows/build.yml/badge.svg)
# eLibrary API


Апи, которое позволяет по ендпоинтам получать информацию о статье с сайта elibrary.ru. Сайт предоставляет только платное
апи, поэтому этот проект сделан, чтобы можно было хоть как-то автоматизировать получение информации с сайта.

Поскольку данные получаются простым парсингом html, то какие-нибудь данные могут быть не получены (в том числе может 
не спарситься и вся статья)

По отдельным ендпоинтам можно сохранять полученные данные в `google sheets`.

## :exclamation::exclamation:Rate limit:exclamation::exclamation:
У elibrary.ru стоит rate limiter на количество запросов, поэтому спустя 10-15 быстрых запросов сайт начнет просить
пройти капчу, приложение же начнет отдавать 429 ошибку. Чтобы обойти рейт лимит, нужно либо зайти на сайт и 
пройти капчу вручную, либо писать какой-нибудь динамический прокси, для смены ip адрессов в случае ошибок. 
Чтобы сайт начал обращаться к прокси, а не к самому сайту в `application.yml` достаточно поменять `e-library-url` и 
обращаться к нему через прокси.

---

Чтобы начать использовать апи, достаточно поднять его в `docker compose`. Пример настройки описан в `compose.yml`.
Также нужно настроить доступ к google api: https://developers.google.com/sheets/api/quickstart/java?hl=ru, 
скачать оттуда `json` с ключами и передать его в compose.

---
Проект написан на `Kotlin`, с использованием `Spring Boot 3`.

Для теста эндпоинтов присутствует `swagger`.
