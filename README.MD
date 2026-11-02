## Spring boot admin to Forum app   
Simple setup with Spring boot admin, to check my Forum app 

## How to start the project
Needs Java 17+.

```mvn clean install -DskipTests && mvn spring-boot:run```

With Docker: `docker build -t forum-admin . && docker run -p 8081:8081 forum-admin`.
To run it together with the forum (and a Cloudflare Tunnel), use the `docker-compose.yml` in the [forum](https://github.com/osmarpetry/forum) repo.

## How to access the started project
Access in the browser, the following link:
```http://localhost:8081/#/applications```

## About security 
Is important to setup security here, in the application (in the other repo.) just for this project has access and here, just for specific people have access to the domain, today is public

Ref: ``https://codecentric.github.io/spring-boot-admin/current/#register-client-applications`` and `https://codecentric.github.io/spring-boot-admin/current/#securing-spring-boot-admin`
