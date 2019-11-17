# JHipster Performance Bench

## JDK

I used OpenJDK 11 and didn't check other versions.

```
❯ java --version
openjdk version "11.0.4" 2019-07-16
OpenJDK Runtime Environment (build 11.0.4+11-post-Ubuntu-1ubuntu219.04)
OpenJDK 64-Bit Server VM (build 11.0.4+11-post-Ubuntu-1ubuntu219.04, mixed mode)
```

## How to use

```
❯ ./mvnw clean package
❯ (cd 99-benchmark-launcher/; java -jar target/*.jar)
```

Then it shows results.
