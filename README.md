## Spring Boot Actuator Endpoint to visualize beans hierarchy

This library adds `/beansviz` endpoint to your actuator.

![image](https://cloud.githubusercontent.com/assets/106908/22554428/539f9f5c-e9a3-11e6-9f4e-ee4b46f080ce.png)


* [Demo](https://demo-beansviz.cfapps.io/beansviz)
* [Demo (All Beans)](https://demo-beansviz.cfapps.io/beansviz?all=true)

### Maven dependency
    

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
            
    <dependency>
        <groupId>am.ik.beansviz</groupId>
        <artifactId>beansviz-spring-boot-actuator</artifactId>
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