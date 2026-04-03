Инструкции по запуску

1. Клонировать репозиторий
2. cd в корень проекта
3. поднимаем бд. выполнить команду: docker compose up
   *убедитесь что докер установлен и запущен*
4. билд проекта, выполнить команду в втором табе терминала:
   ./gradlew clean build  
   Или в intelij idea clean build
5. Запуск проекта

Примеры api

1. POST /api/v1/payments - создание платежа
   ![img.png](img.png)


2. GET /api/v1/payments/{id} - получение платежа по id
   ![img_2.png](img_2.png)


3. POST /api/v1/payments/{id}/confirm - подтверждение платежа
   ![img_3.png](img_3.png)


4. POST /api/v1/payments/{id}/cancel - отмена платежа
   ![img_4.png](img_4.png)


5. GET /api/v1/clients/{id}/payments - получение всех платежей клиента
   ![img_5.png](img_5.png)


7. Валидация данных и обработка ошибок реализованна для ВСЕХ ендпоинтов. Пару примеров:  
   amount не положительный
   ![img_1.png](img_1.png)


валюта не валидная
![img_6.png](img_6.png)


клиент не найден
![img_7.png](img_7.png)

и так далее. 