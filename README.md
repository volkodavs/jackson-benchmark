[![CircleCI](https://circleci.com/gh/volkodavs/jackson-benchmark.svg?style=svg)](https://circleci.com/gh/volkodavs/jackson-benchmark)


# Java Jackson Benchmarks

## Introduction

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

## Environment 

* 8 CPU
* 64 GB RAM
* OS version: 16.04.1 LTS (Xenial Xerus)
 
## Run benchmarks

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
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.JacksonBenchmark.bigJacksonStreamRead
# Parameters: (arraySize = 10000)

```

#### Raw Results

```
Benchmark                                                                 (arraySize)   Mode  Cnt           Score           Error   Units
JacksonBenchmark.bigJacksonStreamRead                                           10000  thrpt   50           0.249 ±         0.005   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                            10000  thrpt   50         560.932 ±         3.960  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm                       10000  thrpt   50  6421832040.320 ±    100120.430    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space                   10000  thrpt   50         559.905 ±        25.938  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm              10000  thrpt   50  6410280632.320 ± 294605368.555    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen                      10000  thrpt   50           0.180 ±         0.205  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm                 10000  thrpt   50     2055178.400 ±   2344893.644    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Survivor_Space               10000  thrpt   50          21.819 ±         0.464  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Survivor_Space.norm          10000  thrpt   50   249770803.200 ±   4629784.591    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                                 10000  thrpt   50         234.000                  counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                                  10000  thrpt   50       23100.000                      ms
JacksonBenchmark.bigJacksonStreamWrite                                          10000  thrpt   50           0.355 ±         0.007   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                           10000  thrpt   50         503.641 ±         2.803  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm                      10000  thrpt   50  5174840731.360 ±   7363993.266    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space                  10000  thrpt   50         504.728 ±        10.652  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm             10000  thrpt   50  5186047180.800 ± 106020299.004    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Survivor_Space              10000  thrpt   50          21.451 ±         2.457  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Survivor_Space.norm         10000  thrpt   50   220410675.200 ±  25183205.753    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                                10000  thrpt   50         201.000                  counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                                 10000  thrpt   50       22413.000                      ms
JacksonBenchmark.bigJsonRead                                                    10000  thrpt   50           0.224 ±         0.003   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                                     10000  thrpt   50         567.880 ±         4.348  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                                10000  thrpt   50  6832802017.600 ±  12820811.297    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                            10000  thrpt   50         567.899 ±        26.586  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm                       10000  thrpt   50  6829375488.000 ± 295180647.279    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                               10000  thrpt   50           2.076 ±         1.458  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                          10000  thrpt   50    25228707.200 ±  17767936.958    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Survivor_Space                        10000  thrpt   50          20.655 ±         0.957  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Survivor_Space.norm                   10000  thrpt   50   248596398.080 ±  11687734.816    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                          10000  thrpt   50         247.000                  counts
JacksonBenchmark.bigJsonRead:·gc.time                                           10000  thrpt   50       27354.000                      ms
JacksonBenchmark.bigJsonWrite                                                   10000  thrpt   50           0.367 ±         0.009   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                                    10000  thrpt   50         503.487 ±         3.461  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                               10000  thrpt   50  5106348258.880 ±  14230120.246    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                           10000  thrpt   50         503.433 ±        19.155  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm                      10000  thrpt   50  5106145689.600 ± 193153920.719    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space                       10000  thrpt   50          20.087 ±         3.452  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space.norm                  10000  thrpt   50   203801231.360 ±  35033582.502    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                                         10000  thrpt   50         187.000                  counts
JacksonBenchmark.bigJsonWrite:·gc.time                                          10000  thrpt   50       21869.000                      ms
JacksonBenchmark.tinyJacksonStreamRead                                          10000  thrpt   50         195.107 ±         3.794   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                           10000  thrpt   50         517.868 ±         5.244  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm                      10000  thrpt   50  3887516501.920 ±  12804270.299    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space                  10000  thrpt   50         517.026 ±        42.699  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm             10000  thrpt   50  3884554649.600 ± 329030123.671    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Survivor_Space              10000  thrpt   50          14.480 ±         2.747  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Survivor_Space.norm         10000  thrpt   50   108632473.600 ±  20448474.858    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                                10000  thrpt   50         162.000                  counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                                 10000  thrpt   50       21051.000                      ms
JacksonBenchmark.tinyJacksonStreamWrite                                         10000  thrpt   50         274.221 ±        14.196   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                          10000  thrpt   50         521.865 ±         5.913  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm                     10000  thrpt   50  3895868888.960 ±     28787.463    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space                 10000  thrpt   50         516.417 ±        53.575  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm            10000  thrpt   50  3859598540.800 ± 403895171.487    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen                    10000  thrpt   50           0.063 ±         0.163  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm               10000  thrpt   50      482345.120 ±   1237343.426    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Survivor_Space             10000  thrpt   50          13.688 ±         2.634  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Survivor_Space.norm        10000  thrpt   50   102341017.600 ±  19951941.160    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                               10000  thrpt   50         156.000                  counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                                10000  thrpt   50       20889.000                      ms
JacksonBenchmark.tinyJsonRead                                                   10000  thrpt   50         137.040 ±         3.908   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                                    10000  thrpt   50         521.534 ±         5.095  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                               10000  thrpt   50  3901634821.120 ±     56989.457    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                           10000  thrpt   50         522.878 ±        42.098  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm                      10000  thrpt   50  3913201745.920 ± 317706324.316    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                              10000  thrpt   50           0.123 ±         0.430  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm                         10000  thrpt   50      964689.920 ±   3376841.973    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Survivor_Space                       10000  thrpt   50          15.584 ±         3.513  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Survivor_Space.norm                  10000  thrpt   50   116643594.240 ±  26294696.841    B/op
JacksonBenchmark.tinyJsonRead:·gc.count                                         10000  thrpt   50         165.000                  counts
JacksonBenchmark.tinyJsonRead:·gc.time                                          10000  thrpt   50       21058.000                      ms
JacksonBenchmark.tinyJsonWrite                                                  10000  thrpt   50         232.968 ±        10.174   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                                   10000  thrpt   50         518.442 ±         4.900  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                              10000  thrpt   50  3887558235.360 ±  10078545.655    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                          10000  thrpt   50         521.336 ±        45.722  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm                     10000  thrpt   50  3912908144.640 ± 349637821.158    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Survivor_Space                      10000  thrpt   50          15.334 ±         3.347  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Survivor_Space.norm                 10000  thrpt   50   115175587.840 ±  25368208.953    B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                                        10000  thrpt   50         162.000                  counts
JacksonBenchmark.tinyJsonWrite:·gc.time                                         10000  thrpt   50       20990.000                      ms

---
Run complete. Total time: 01:58:37
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
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.JacksonBenchmark.bigJacksonStreamRead
# Parameters: (arraySize = 10000)
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
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.JacksonBenchmark.bigJacksonStreamRead
# Parameters: (arraySize = 10000)
```

#### Raw Results

```

```

## Graph

### 10,000 Element Array



## Summary 


### Comparison tables


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
