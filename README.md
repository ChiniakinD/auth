## Spring boot REST API для аутентификации и авторизации пользователей.

## Запуск проекта с использованием Docker
1. Создать контейнер PostgreSQL:
```bash
docker run --name auth -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

2. Запустить контейнер PostgreSQL:
```bash
docker start auth
```