# Spring Boot Admin do Forum

Painel que monitora o app [forum](https://github.com/osmarpetry/forum) (health, logs, metricas via actuator).
Spring Boot 2.1 + spring-boot-admin-server 2.1 (Java 8). Porta **8081**.

## Rodar com Docker (forum + admin)

O `docker-compose.yml` fica no repo `forum`. Os 2 repos clonados lado a lado:

```bash
git clone https://github.com/osmarpetry/forum.git
git clone https://github.com/osmarpetry/forum-springbootadmin.git
cd forum
docker compose up --build
```

Abrir: http://localhost:8081

## Rodar so o admin

Com Docker:

```bash
docker build -t forum-admin . && docker run --rm -p 8081:8081 forum-admin
```

Sem Docker (Java 8+):

```bash
./mvnw spring-boot:run
```

## Criterios de aceite (AC)

1. http://localhost:8081 abre o painel (HTTP 200).
2. `POST /instances` registra uma aplicacao (HTTP 201).
3. `GET /instances` lista a aplicacao registrada.
4. Com o forum rodando, ele aparece no painel com status UP.

## Como testar

Automatico (cobre AC 2 e 3):

```bash
./mvnw test
```

Manual, com o admin rodando:

```bash
curl -s -o /dev/null -w '%{http_code}\n' localhost:8081/
```

```bash
curl -s localhost:8081/instances
```

AC 4: suba o forum (veja o README do repo `forum`) e abra http://localhost:8081.

## Dependabot

- Roda toda semana (`.github/dependabot.yml`).
- So abre PR de **patch**. Minor e major sao ignorados: Spring Boot e spring-boot-admin-server precisam subir juntos, e isso e manual.
- PR do dependabot com CI verde faz merge sozinho (`.github/workflows/ci.yml`).

## Seguranca (conhecido)

O painel e publico (sem login). Para producao, ver:
- https://codecentric.github.io/spring-boot-admin/current/#securing-spring-boot-admin
- https://codecentric.github.io/spring-boot-admin/current/#register-client-applications
