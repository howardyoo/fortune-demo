# fortune-demo

- This particular fortune-demo can either be used as standalone springboot application or be used with Wavefront by VMware's 
new Wavefront Springboot Autoconfigure

## Redis is required
- just get the docker image and run, make sure to expose port 
6379

```
docker run -p 127.0.0.1:6379:6379/tcp --name redis -d redis
```

## Wavefront SpringBoot Actuator
- In order to enable it, modify pom.xml and uncomment the following
dependency

```
<dependency>
	<groupId>com.wavefront</groupId>
	<artifactId>wavefront-spring-boot-autoconfigure</artifactId>
	<version>2.0.0-SNAPSHOT</version>
</dependency>
```

For more information on the actuator, visit
https://github.com/wavefrontHQ/wavefront-spring-boot