[![Java](https://img.shields.io/badge/Java-8%2B-orange)](https://www.oracle.com/java/)
![Lines of Code](https://img.shields.io/badge/lines_of_code-1151-green)
![License](https://img.shields.io/badge/license-MIT-blue)

**Plain HTTPS** is a simple and lightweight library that provides efficient HTTPS client.

How to use:
```xml
<dependency>
    <groupId>io.github.kyljmeeski</groupId>
    <artifactId>plain-https</artifactId>
    <version>0.0.1</version>
</dependency>
```

## GET Request
```java
Request request = new GetRequest("httpbin.org/get")
        .with("Accept", "*/*")
        .with("Accept-Language", "en-US");
Response response = request.execute();
if (response.status().code() == 200) {
    Body body = response.body();
}
```

## POST Request
You can set a string as a body:
```java
Body requestBody = new TextBody("hello");
Request request = new PostRequest("httpbin.org/get")
        .containing(requestBody)
        .with("Accept", "*/*");
Response response = request.execute();
if (response.status().code() == 200) {
    Body responseBody = response.body();
}
```

... or object of any class:
```java
class Player {
    String name;
    int age;
    
    // constructor
}
```

```java
Body requestBody = new JsonBody(new Player("messi", 36));
Request request = new PostRequest("httpbin.org/get")
        .containing(requestBody)
        .with("Accept", "*/*");
Response response = request.execute();
if (response.status().code() == 200) {
    Body responseBody = response.body();
}
```

## Response with Text Body
```java
Body body = response.body();
if (body.type.equals("text/plain")) {
    System.out.println(new String(body.content()));
}
```

## Response with JSON Body:
```java
Body body = response.body();
if (body.type.equals("application/json")) {
    Player player = new BodyWrapper(body).content(Player.class);
}
```
