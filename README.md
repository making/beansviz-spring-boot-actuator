## Spring Boot Actuator Endpoint to visualize beans hierarchy

This library adds `/beansviz` endpoint to your actuator.

### Maven dependency
    

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
            
    <dependency>
        <groupId>am.ik.beansviz</groupId>
        <artifactId>spring-boot-actuator-beansviz</artifactId>
        <version>0.1.0-SNAPSHOT</version>
    </dependency>
    
    <!-- ... -->
</dependencies>
```

Add the following repository to use snapshots.


```xml
<repository>
    <id>sonatype-snapshots</id>
    <name>Sonatype Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

* [Demo](https://demo-beansviz.cfapps.io/beansviz)
* [Demo (All Beans)](https://demo-beansviz.cfapps.io/beansviz?all=true)