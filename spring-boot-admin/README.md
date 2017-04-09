# Spring Boot Admin Server

Spring Boot Admin is a simple application to manage and monitor your Spring Boot Applications


### Developers

- [Anatoly Cherkasov](https://github.com/avcherkasov)


### Settings on the client

`pom.xml`
```
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>1.4.6</version>
</dependency>
```

`logback.xml`
```
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    <jmxConfigurator/>
</configuration>
```

`application.properties`
```
spring.boot.admin.url=http://{spring-boot-admin-server}:{port}
```

#### Some errors

To fix the "Full authentication is required to access this resource" error, add the following lines to the `application.properties`

```
endpoints.sensitive=false
```

###  I want to know more

- [Spring Boot Admin Reference Guide](http://codecentric.github.io/spring-boot-admin/1.4.6/)
- [Spring Boot Admin Github](https://github.com/codecentric/spring-boot-admin)
