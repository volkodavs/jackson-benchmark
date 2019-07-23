[![CircleCI](https://circleci.com/gh/volkodavs/jackson-benchmark.svg?style=svg)](https://circleci.com/gh/volkodavs/jackson-benchmark)


# Java Jackson Benchmarks

## Introduction

The idea of the project is to compare Jackson Stream API performance with Jackson Mappers. 

* Plain  ~ 72B
* Complex ~ 52KB

### Tiny 

#### Structure 

```java
public class Sport{
    Long id;
    String name;
}
```

### Big 

Single event object contains: 
* 10 markets
* 20 runners 
* 20 event participants

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
Benchmark                                                              Mode  Cnt        Score       Error   Units
JacksonBenchmark.bigJacksonStreamRead                                    thrpt   50     2783.438 ±     8.696   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                     thrpt   50      638.612 ±     2.167  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm                thrpt   50   252808.170 ±   753.372    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space            thrpt   50      639.857 ±    51.236  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm       thrpt   50   253278.471 ± 20224.219    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen               thrpt   50        0.004 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm          thrpt   50        1.389 ±     0.549    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                          thrpt   50      137.000              counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                           thrpt   50     1881.000                  ms
JacksonBenchmark.bigJsonRead                                             thrpt   50     2437.580 ±    16.192   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                              thrpt   50      654.186 ±     4.544  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                         thrpt   50   295676.731 ±   841.686    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                     thrpt   50      653.944 ±    46.710  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm                thrpt   50   295640.513 ± 21275.105    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                        thrpt   50        0.004 ±     0.002  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                   thrpt   50        1.918 ±     0.690    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                   thrpt   50      140.000              counts
JacksonBenchmark.bigJsonRead:·gc.time                                    thrpt   50     1947.000                  ms
JacksonBenchmark.bigJacksonStreamWrite                                   thrpt   50     4452.573 ±    25.720   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                    thrpt   50      293.150 ±     1.634  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm               thrpt   50    72540.906 ±    77.507    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space           thrpt   50      289.564 ±    49.881  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm      thrpt   50    71618.812 ± 12260.921    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen              thrpt   50        0.005 ±     0.007  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm         thrpt   50        1.264 ±     1.756    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Survivor_Space       thrpt   50        0.019 ±     0.029  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Survivor_Space.norm  thrpt   50        4.697 ±     7.047    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                         thrpt   50       62.000              counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                          thrpt   50     1004.000                  ms
JacksonBenchmark.bigJsonWrite                                            thrpt   50     4423.017 ±    40.770   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                             thrpt   50      488.272 ±     4.752  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                        thrpt   50   121645.441 ±  1243.170    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                    thrpt   50      499.809 ±    40.529  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm               thrpt   50   124490.468 ±  9966.077    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen                       thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen.norm                  thrpt   50        0.312 ±     0.180    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                                  thrpt   50      107.000              counts
JacksonBenchmark.bigJsonWrite:·gc.time                                   thrpt   50     1495.000                  ms
JacksonBenchmark.tinyJacksonStreamRead                                   thrpt   50  2170555.253 ± 82429.117   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                    thrpt   50     1685.119 ±    18.200  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm               thrpt   50      858.432 ±    20.404    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space           thrpt   50     1676.851 ±    44.874  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm      thrpt   50      853.706 ±    25.141    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen              thrpt   50        0.005 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen.norm         thrpt   50        0.002 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                         thrpt   50      359.000              counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                          thrpt   50     4795.000                  ms
JacksonBenchmark.tinyJacksonStreamWrite                                  thrpt   50  3545584.394 ± 44895.517   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                   thrpt   50     1261.515 ±    15.972  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm              thrpt   50      392.020 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space          thrpt   50     1261.117 ±    57.204  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm     thrpt   50      391.847 ±    16.741    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen             thrpt   50        0.004 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm        thrpt   50        0.001 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                        thrpt   50      270.000              counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                         thrpt   50     3412.000                  ms
JacksonBenchmark.tinyJsonRead                                            thrpt   50  1782984.423 ± 78334.317   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                             thrpt   50     1589.591 ±    45.119  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                        thrpt   50      985.318 ±    22.254    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                    thrpt   50     1588.106 ±    61.813  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm               thrpt   50      983.672 ±    28.595    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                       thrpt   50        0.005 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm                  thrpt   50        0.003 ±     0.001    B/op
JacksonBenchmark.tinyJsonRead:·gc.count                                  thrpt   50      340.000              counts
JacksonBenchmark.tinyJsonRead:·gc.time                                   thrpt   50     4707.000                  ms
JacksonBenchmark.tinyJsonWrite                                           thrpt   50  2719885.003 ± 32318.056   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                            thrpt   50     1451.101 ±    15.252  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                       thrpt   50      587.866 ±     1.999    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                   thrpt   50     1447.981 ±    46.714  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm              thrpt   50      586.629 ±    18.008    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen                      thrpt   50        0.005 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen.norm                 thrpt   50        0.002 ±     0.001    B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                                 thrpt   50      310.000              counts
JacksonBenchmark.tinyJsonWrite:·gc.time                                  thrpt   50     4162.000                  ms
---

Run complete. Total time: 02:20:33
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
Benchmark                                                              Mode  Cnt        Score        Error   Units
JacksonBenchmark.bigJacksonStreamRead                                 thrpt   50     2770.705 ±     23.331   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                  thrpt   50      574.115 ±      4.398  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm             thrpt   50   228328.967 ±    773.984    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space         thrpt   50      574.458 ±     58.192  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm    thrpt   50   228394.768 ±  22899.346    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen            thrpt   50        0.002 ±      0.001  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm       thrpt   50        0.708 ±      0.296    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                       thrpt   50      123.000               counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                        thrpt   50     1265.000                   ms
JacksonBenchmark.bigJacksonStreamWrite                                thrpt   50     4130.809 ±     89.081   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                 thrpt   50      259.100 ±      5.581  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm            thrpt   50    69111.401 ±     65.189    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space        thrpt   50      261.554 ±     37.948  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm   thrpt   50    69717.677 ±   9711.040    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen           thrpt   50       ≈ 10⁻³               MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm      thrpt   50        0.131 ±      0.177    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                      thrpt   50       56.000               counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                       thrpt   50      399.000                   ms
JacksonBenchmark.bigJsonWrite                                         thrpt   50     4427.961 ±     23.855   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                          thrpt   50      252.599 ±      4.911  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                     thrpt   50    62854.285 ±   1243.701    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                 thrpt   50      256.880 ±     35.020  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm            thrpt   50    63942.043 ±   8785.069    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen                    thrpt   50        0.001 ±      0.001  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen.norm               thrpt   50        0.245 ±      0.367    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space             thrpt   50        0.011 ±      0.023  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space.norm        thrpt   50        2.838 ±      5.617    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                               thrpt   50       55.000               counts
JacksonBenchmark.bigJsonWrite:·gc.time                                thrpt   50      529.000                   ms
JacksonBenchmark.bigJsonRead                                          thrpt   50     2444.855 ±     21.058   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                           thrpt   50      602.380 ±      4.878  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                      thrpt   50   271462.609 ±    932.309    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                  thrpt   50      607.227 ±     57.208  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm             thrpt   50   273727.771 ±  25917.011    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                     thrpt   50        0.002 ±      0.001  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                thrpt   50        0.864 ±      0.318    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                thrpt   50      130.000               counts
JacksonBenchmark.bigJsonRead:·gc.time                                 thrpt   50     1516.000                   ms
JacksonBenchmark.tinyJacksonStreamRead                                thrpt   50  2246011.065 ± 115879.934   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                 thrpt   50     1561.536 ±     39.207  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm            thrpt   50      770.420 ±     24.206    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space        thrpt   50     1564.764 ±     62.883  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm   thrpt   50      771.894 ±     34.097    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen           thrpt   50        0.001 ±      0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen.norm      thrpt   50       ≈ 10⁻³                 B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                      thrpt   50      335.000               counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                       thrpt   50     3317.000                   ms
JacksonBenchmark.tinyJacksonStreamWrite                               thrpt   50  3185827.775 ±  75512.088   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                thrpt   50     1133.486 ±     26.857  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm           thrpt   50      392.014 ±      0.001    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space       thrpt   50     1134.990 ±     61.826  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm  thrpt   50      392.212 ±     17.650    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen          thrpt   50       ≈ 10⁻³               MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm     thrpt   50       ≈ 10⁻⁴                 B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                     thrpt   50      243.000               counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                      thrpt   50     2467.000                   ms
JacksonBenchmark.tinyJsonRead                                         thrpt   50  1871220.307 ±  60270.647   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                          thrpt   50     1534.262 ±     22.064  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                     thrpt   50      905.624 ±     19.501    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                 thrpt   50     1536.750 ±     57.666  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm            thrpt   50      907.373 ±     38.782    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                    thrpt   50        0.001 ±      0.001  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm               thrpt   50       ≈ 10⁻³                 B/op
JacksonBenchmark.tinyJsonRead:·gc.count                               thrpt   50      329.000               counts
JacksonBenchmark.tinyJsonRead:·gc.time                                thrpt   50     3385.000                   ms
JacksonBenchmark.tinyJsonWrite                                        thrpt   50  2720099.204 ±  33028.551   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                         thrpt   50     1270.384 ±     15.911  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                    thrpt   50      514.576 ±      1.866    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                thrpt   50     1275.141 ±     58.185  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm           thrpt   50      516.445 ±     22.385    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen                   thrpt   50        0.001 ±      0.001  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen.norm              thrpt   50       ≈ 10⁻⁴                 B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                              thrpt   50      273.000               counts
JacksonBenchmark.tinyJsonWrite:·gc.time                               thrpt   50     2677.000                   ms
--- 

# Run complete. Total time: 02:20:36
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

Benchmark                                                                 Mode  Cnt        Score       Error   Units
JacksonBenchmark.bigJacksonStreamRead                                    thrpt   50     2799.989 ±    24.638   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                     thrpt   50      581.410 ±     5.043  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm                thrpt   50   228808.335 ±   882.054    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space            thrpt   50      579.130 ±    58.334  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm       thrpt   50   227850.569 ± 22724.254    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen               thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm          thrpt   50        0.656 ±     0.254    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                          thrpt   50      124.000              counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                           thrpt   50      441.000                  ms
JacksonBenchmark.bigJacksonStreamWrite                                   thrpt   50     3868.468 ±    80.472   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                    thrpt   50      242.704 ±     5.039  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm               thrpt   50    69124.784 ±    71.752    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space           thrpt   50      238.198 ±    16.355  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm      thrpt   50    67881.682 ±  4343.295    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen              thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm         thrpt   50        0.126 ±     0.181    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Survivor_Space       thrpt   50        0.015 ±     0.026  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Survivor_Space.norm  thrpt   50        4.419 ±     7.503    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                         thrpt   50       51.000              counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                          thrpt   50      195.000                  ms
JacksonBenchmark.bigJsonWrite                                            thrpt   50     4427.390 ±    32.559   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                             thrpt   50      263.209 ±     1.862  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                        thrpt   50    65500.561 ±    71.475    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                    thrpt   50      266.231 ±    40.512  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm               thrpt   50    66284.376 ± 10143.237    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen                       thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen.norm                  thrpt   50        0.095 ±     0.167    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space                thrpt   50        0.008 ±     0.019  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space.norm           thrpt   50        1.911 ±     4.681    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                                  thrpt   50       57.000              counts
JacksonBenchmark.bigJsonWrite:·gc.time                                   thrpt   50      210.000                  ms
JacksonBenchmark.bigJsonRead                                             thrpt   50     2456.241 ±    19.878   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                              thrpt   50      604.279 ±     4.993  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                         thrpt   50   271051.819 ±   736.666    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                     thrpt   50      602.556 ±    57.656  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm                thrpt   50   270291.021 ± 25820.684    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                        thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                   thrpt   50        0.761 ±     0.333    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                   thrpt   50      129.000              counts
JacksonBenchmark.bigJsonRead:·gc.time                                    thrpt   50      458.000                  ms
JacksonBenchmark.tinyJacksonStreamRead                                   thrpt   50  2224497.325 ± 92810.021   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                    thrpt   50     1568.815 ±    30.284  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm               thrpt   50      780.020 ±    19.701    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space           thrpt   50     1578.680 ±    59.799  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm      thrpt   50      784.436 ±    29.941    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen              thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen.norm         thrpt   50        0.001 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                         thrpt   50      338.000              counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                          thrpt   50     1231.000                  ms
JacksonBenchmark.tinyJacksonStreamWrite                                  thrpt   50  3254260.574 ± 66670.117   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                   thrpt   50     1157.835 ±    23.724  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm              thrpt   50      392.014 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space          thrpt   50     1158.346 ±    52.015  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm     thrpt   50      392.096 ±    15.163    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen             thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm        thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                        thrpt   50      248.000              counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                         thrpt   50      874.000                  ms
JacksonBenchmark.tinyJsonRead                                            thrpt   50  1909429.227 ± 80772.214   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                             thrpt   50     1568.459 ±    28.653  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                        thrpt   50      908.824 ±    24.017    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                    thrpt   50     1569.297 ±    66.213  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm               thrpt   50      908.363 ±    37.470    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                       thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm                  thrpt   50       ≈ 10⁻³                B/op
JacksonBenchmark.tinyJsonRead:·gc.count                                  thrpt   50      336.000              counts
JacksonBenchmark.tinyJsonRead:·gc.time                                   thrpt   50     1237.000                  ms
JacksonBenchmark.tinyJsonWrite                                           thrpt   50  2788025.395 ± 52456.911   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                            thrpt   50     1305.665 ±    23.804  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                       thrpt   50      516.016 ±     2.000    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                   thrpt   50     1298.503 ±    57.971  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm              thrpt   50      513.331 ±    21.872    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen                      thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen.norm                 thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                                 thrpt   50      278.000              counts
JacksonBenchmark.tinyJsonWrite:·gc.time                                  thrpt   50      989.000                  ms
---

# Run complete. Total time: 02:20:33
```

## Results  

### Plain object 
<img width="518" alt="Plain object" src="https://user-images.githubusercontent.com/4140597/61590467-c984de80-abb1-11e9-8912-6780bcf7954a.png">

### Complex object 
<img width="511" alt="Complex object" src="https://user-images.githubusercontent.com/4140597/61590473-d570a080-abb1-11e9-9faf-85f36e322626.png">

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
