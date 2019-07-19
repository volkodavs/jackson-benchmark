[![CircleCI](https://circleci.com/gh/volkodavs/jackson-benchmark.svg?style=svg)](https://circleci.com/gh/volkodavs/jackson-benchmark)


# Java Jackson Benchmarks

## Introduction

The idea of the project is to compare Jackson Stream API performance with Jackson Mappers

### Tiny Object 
size: 72B

#### Structure 

```java
public class Sport{
    Long id;
    String name;
}
```

### Big Object 

Single event object contains: 
* 10 markets
* 20 runners 
* 20 event participants

size: 52KB

#### Structure 

```java
public class Event {
    Long id;
    Long sportId;
    String name;
    Date startTime;
    boolean inRunning;
    boolean allowLiveBetting;
    List<Market> markets;
    List<EventParticipant> eventParticipants;
}

public class Market {
    Long id;
    Long eventId;
    String name;
    boolean inRunning;
    boolean allowLiveBetting;
    Double value;
    Integer winners;
    List<Runner> runners;
}

public class Runner {
    Long id;
    Long eventId;
    Long marketId;
    Long eventParticipantId;
    String name;
    boolean withdrawn;
    Double value;
}

public class EventParticipant{
    Long id;
    Long eventId;
    String participantName;
    String jockeyName;
    String trainerName;
    Integer number;
}
```
 
## Benchmarks

Environment 

* 8 CPU
* 64 GB RAM
* OS version 16.04.1 LTS (Xenial Xerus)
* Docker version 18.03.0-ce, build 0520e24

### Java 8

```commandline
docker run -it volkodav/java-jackson-benchmark:java8
```

#### Params

```
# JMH version: 1.21
# VM version: JDK 1.8.0_222, OpenJDK 64-Bit Server VM, 25.222-b10
# VM invoker: /usr/local/openjdk-8/bin/java
# VM options: -XX:+UseG1GC -server -Xmx4096m -Xms4096m
# Warmup: 10 iterations, 10 s each
# Measurement: 10 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
```

#### Raw Results

```

---


```

### Java 11

```commandline
docker run -it volkodav/java-jackson-benchmark:java11
```

#### Params 

```
# JMH version: 1.21
# VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11
# VM invoker: /usr/local/openjdk-11/bin/java
# VM options: -XX:+UseG1GC -server -Xmx4096m -Xms4096m
# Warmup: 10 iterations, 10 s each
# Measurement: 10 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
```

#### Raw Results

```

```

### Java 12

```commandline
docker run -it volkodav/java-jackson-benchmark:java12
```


#### Params

```
# JMH version: 1.21
# VM version: JDK 12.0.2, OpenJDK 64-Bit Server VM, 12.0.2+10
# VM invoker: /usr/java/openjdk-12/bin/java
# VM options: -XX:+UseG1GC -server -Xmx4096m -Xms4096m
# Warmup: 10 iterations, 10 s each
# Measurement: 10 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
```

#### Raw Results

```

```

## Graph


## Summary 




## License
-------
    MIT License
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
