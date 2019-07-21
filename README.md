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
JacksonBenchmark.bigJacksonStreamRead                                 thrpt   50     2754.665 ±    10.205   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                  thrpt   50      633.033 ±     2.221  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm             thrpt   50   253221.715 ±   917.460    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space         thrpt   50      635.180 ±    52.435  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm    thrpt   50   254071.943 ± 20958.210    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen            thrpt   50        0.003 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm       thrpt   50        1.252 ±     0.454    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                       thrpt   50      136.000              counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                        thrpt   50     1935.000                  ms
JacksonBenchmark.bigJsonRead                                          thrpt   50     2427.268 ±    16.871   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                           thrpt   50      650.791 ±     4.054  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                      thrpt   50   295396.877 ±   884.872    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                  thrpt   50      649.282 ±    48.376  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm             thrpt   50   294730.195 ± 21983.639    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                     thrpt   50        0.004 ±     0.002  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                thrpt   50        1.969 ±     0.776    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                thrpt   50      139.000              counts
JacksonBenchmark.bigJsonRead:·gc.time                                 thrpt   50     2009.000                  ms
JacksonBenchmark.bigJacksonStreamWrite                                thrpt   50     4292.404 ±    20.830   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                 thrpt   50      516.359 ±     2.373  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm            thrpt   50   132556.521 ±    91.475    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space        thrpt   50      527.744 ±    51.223  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm   thrpt   50   135444.117 ± 13018.969    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen           thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm      thrpt   50        0.491 ±     0.242    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                      thrpt   50      113.000              counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                       thrpt   50     1743.000                  ms
JacksonBenchmark.bigJsonWrite                                         thrpt   50     4388.999 ±    40.128   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                          thrpt   50      493.077 ±     4.480  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                     thrpt   50   123774.600 ±    73.073    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                 thrpt   50      499.753 ±    40.479  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm            thrpt   50   125411.144 ±  9887.025    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen                    thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen.norm               thrpt   50        0.412 ±     0.205    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                               thrpt   50      107.000              counts
JacksonBenchmark.bigJsonWrite:·gc.time                                thrpt   50     1635.000                  ms
JacksonBenchmark.tinyJacksonStreamRead                                thrpt   50  2150609.022 ± 81846.116   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                 thrpt   50     1669.648 ±    20.195  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm            thrpt   50      858.433 ±    20.404    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space        thrpt   50     1672.134 ±    48.841  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm   thrpt   50      860.018 ±    32.354    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen           thrpt   50        0.005 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen.norm      thrpt   50        0.002 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                      thrpt   50      358.000              counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                       thrpt   50     4685.000                  ms
JacksonBenchmark.tinyJsonRead                                         thrpt   50  1823379.967 ± 76225.192   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                          thrpt   50     1625.880 ±    39.243  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                     thrpt   50      985.798 ±    24.089    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                 thrpt   50     1625.438 ±    51.984  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm            thrpt   50      985.478 ±    31.782    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                    thrpt   50        0.005 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm               thrpt   50        0.003 ±     0.001    B/op
JacksonBenchmark.tinyJsonRead:·gc.count                               thrpt   50      348.000              counts
JacksonBenchmark.tinyJsonRead:·gc.time                                thrpt   50     4433.000                  ms
JacksonBenchmark.tinyJacksonStreamWrite                               thrpt   50  3373531.235 ± 29554.721   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                thrpt   50     1258.588 ±    11.033  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm           thrpt   50      411.061 ±     1.942    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space       thrpt   50     1261.135 ±    57.171  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm  thrpt   50      411.798 ±    17.876    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen          thrpt   50        0.004 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm     thrpt   50        0.001 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                     thrpt   50      270.000              counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                      thrpt   50     3510.000                  ms
JacksonBenchmark.tinyJsonWrite                                        thrpt   50  2677089.873 ± 37114.170   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                         thrpt   50     1428.243 ±    18.559  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                    thrpt   50      587.866 ±     1.999    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                thrpt   50     1429.248 ±    37.952  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm           thrpt   50      588.379 ±    14.440    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen                   thrpt   50        0.004 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen.norm              thrpt   50        0.002 ±     0.001    B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                              thrpt   50      306.000              counts
JacksonBenchmark.tinyJsonWrite:·gc.time                               thrpt   50     4227.000                  ms

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
Benchmark
JacksonBenchmark.bigJacksonStreamRead                                 thrpt   50     2750.104 ±    34.259   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                  thrpt   50      570.584 ±     6.981  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm             thrpt   50   228618.534 ±   922.163    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space         thrpt   50      579.105 ±    58.322  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm    thrpt   50   232141.517 ± 23443.558    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen            thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm       thrpt   50        0.697 ±     0.278    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                       thrpt   50      124.000              counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                        thrpt   50     1475.000                  ms
JacksonBenchmark.bigJsonRead                                          thrpt   50     2418.981 ±    36.585   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                           thrpt   50      595.782 ±     8.818  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                      thrpt   50   271368.481 ±   783.977    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                  thrpt   50      593.202 ±    58.213  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm             thrpt   50   270295.391 ± 26414.307    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                     thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                thrpt   50        0.863 ±     0.350    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                thrpt   50      127.000              counts
JacksonBenchmark.bigJsonRead:·gc.time                                 thrpt   50     1448.000                  ms
JacksonBenchmark.bigJacksonStreamWrite                                thrpt   50     3540.935 ±   158.383   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                 thrpt   50      415.079 ±    18.693  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm            thrpt   50   129153.420 ±   105.760    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space        thrpt   50      420.352 ±    46.710  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm   thrpt   50   130624.030 ± 13976.153    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen           thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm      thrpt   50        0.212 ±     0.113    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                      thrpt   50       90.000              counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                       thrpt   50      766.000                  ms
JacksonBenchmark.bigJsonWrite                                         thrpt   50     4415.327 ±    41.827   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                          thrpt   50      262.637 ±     2.286  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                     thrpt   50    65539.682 ±    96.584    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                 thrpt   50      266.242 ±    40.529  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm            thrpt   50    66409.491 ±  9996.400    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen                    thrpt   50       ≈ 10⁻⁴              MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen.norm               thrpt   50        0.078 ±     0.135    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                               thrpt   50       57.000              counts
JacksonBenchmark.bigJsonWrite:·gc.time                                thrpt   50      438.000                  ms
JacksonBenchmark.tinyJacksonStreamRead                                thrpt   50  2210673.734 ± 85548.178   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                 thrpt   50     1591.781 ±    27.532  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm            thrpt   50      796.020 ±    19.290    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space        thrpt   50     1592.692 ±    60.395  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm   thrpt   50      795.832 ±    28.983    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen           thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen.norm      thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                      thrpt   50      341.000              counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                       thrpt   50     3215.000                  ms
JacksonBenchmark.tinyJsonRead                                         thrpt   50  1852237.578 ± 72630.244   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                          thrpt   50     1518.020 ±    33.451  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                     thrpt   50      905.623 ±    19.503    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                 thrpt   50     1513.345 ±    62.848  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm            thrpt   50      902.923 ±    38.079    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                    thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm               thrpt   50       ≈ 10⁻³                B/op
JacksonBenchmark.tinyJsonRead:·gc.count                               thrpt   50      324.000              counts
JacksonBenchmark.tinyJsonRead:·gc.time                                thrpt   50     3218.000                  ms
JacksonBenchmark.tinyJacksonStreamWrite                               thrpt   50  3167934.441 ± 43935.080   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                thrpt   50     1184.578 ±    16.519  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm           thrpt   50      412.014 ±     2.000    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space       thrpt   50     1191.041 ±    34.996  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm  thrpt   50      414.336 ±    11.290    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen          thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm     thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                     thrpt   50      255.000              counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                      thrpt   50     2695.000                  ms
JacksonBenchmark.tinyJsonWrite                                        thrpt   50  2670052.814 ± 49658.273   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                         thrpt   50     1252.788 ±    23.188  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                    thrpt   50      516.977 ±     1.942    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                thrpt   50     1256.457 ±    56.686  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm           thrpt   50      518.607 ±    21.914    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen                   thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen.norm              thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                              thrpt   50      269.000              counts
JacksonBenchmark.tinyJsonWrite:·gc.time                               thrpt   50     2598.000                  ms

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

Benchmark                                                              Mode  Cnt        Score       Error   Units
JacksonBenchmark.bigJacksonStreamRead                                 thrpt   50     2728.870 ±    30.887   ops/s
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate                  thrpt   50      566.588 ±     7.019  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.alloc.rate.norm             thrpt   50   228775.797 ±   734.014    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space         thrpt   50      565.107 ±    57.633  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Eden_Space.norm    thrpt   50   228246.167 ± 23270.992    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen            thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamRead:·gc.churn.G1_Old_Gen.norm       thrpt   50        0.793 ±     0.316    B/op
JacksonBenchmark.bigJacksonStreamRead:·gc.count                       thrpt   50      121.000              counts
JacksonBenchmark.bigJacksonStreamRead:·gc.time                        thrpt   50      422.000                  ms
JacksonBenchmark.bigJsonRead                                          thrpt   50     2422.055 ±    17.943   ops/s
JacksonBenchmark.bigJsonRead:·gc.alloc.rate                           thrpt   50      596.009 ±     4.263  MB/sec
JacksonBenchmark.bigJsonRead:·gc.alloc.rate.norm                      thrpt   50   271123.828 ±   817.774    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space                  thrpt   50      593.206 ±    58.221  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Eden_Space.norm             thrpt   50   270003.927 ± 26830.559    B/op
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen                     thrpt   50        0.002 ±     0.001  MB/sec
JacksonBenchmark.bigJsonRead:·gc.churn.G1_Old_Gen.norm                thrpt   50        0.804 ±     0.309    B/op
JacksonBenchmark.bigJsonRead:·gc.count                                thrpt   50      127.000              counts
JacksonBenchmark.bigJsonRead:·gc.time                                 thrpt   50      448.000                  ms
JacksonBenchmark.bigJacksonStreamWrite                                thrpt   50     3592.260 ±   187.938   ops/s
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate                 thrpt   50      421.000 ±    22.029  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.alloc.rate.norm            thrpt   50   129133.714 ±    92.155    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space        thrpt   50      434.358 ±    52.282  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm   thrpt   50   133043.378 ± 14843.850    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen           thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.bigJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm      thrpt   50        0.193 ±     0.118    B/op
JacksonBenchmark.bigJacksonStreamWrite:·gc.count                      thrpt   50       93.000              counts
JacksonBenchmark.bigJacksonStreamWrite:·gc.time                       thrpt   50      320.000                  ms
JacksonBenchmark.bigJsonWrite                                         thrpt   50     4432.645 ±    18.959   ops/s
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate                          thrpt   50      259.438 ±     3.634  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.alloc.rate.norm                     thrpt   50    64492.506 ±  1017.910    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space                 thrpt   50      266.230 ±    40.517  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Eden_Space.norm            thrpt   50    66163.377 ± 10041.355    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen                    thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Old_Gen.norm               thrpt   50        0.110 ±     0.160    B/op
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space             thrpt   50        0.015 ±     0.026  MB/sec
JacksonBenchmark.bigJsonWrite:·gc.churn.G1_Survivor_Space.norm        thrpt   50        3.797 ±     6.438    B/op
JacksonBenchmark.bigJsonWrite:·gc.count                               thrpt   50       57.000              counts
JacksonBenchmark.bigJsonWrite:·gc.time                                thrpt   50      222.000                  ms
JacksonBenchmark.tinyJacksonStreamRead                                thrpt   50  2224710.864 ± 78925.983   ops/s
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate                 thrpt   50     1575.441 ±    22.824  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.alloc.rate.norm            thrpt   50      782.420 ±    16.476    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space        thrpt   50     1574.064 ±    51.255  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Eden_Space.norm   thrpt   50      782.241 ±    31.610    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen           thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJacksonStreamRead:·gc.churn.G1_Old_Gen.norm      thrpt   50        0.001 ±     0.001    B/op
JacksonBenchmark.tinyJacksonStreamRead:·gc.count                      thrpt   50      337.000              counts
JacksonBenchmark.tinyJacksonStreamRead:·gc.time                       thrpt   50     1232.000                  ms
JacksonBenchmark.tinyJsonRead                                         thrpt   50  1877135.961 ± 74431.201   ops/s
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate                          thrpt   50     1533.838 ±    31.787  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.alloc.rate.norm                     thrpt   50      903.221 ±    20.827    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space                 thrpt   50     1532.005 ±    62.492  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Eden_Space.norm            thrpt   50      901.827 ±    36.633    B/op
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen                    thrpt   50        0.001 ±     0.001  MB/sec
JacksonBenchmark.tinyJsonRead:·gc.churn.G1_Old_Gen.norm               thrpt   50       ≈ 10⁻³                B/op
JacksonBenchmark.tinyJsonRead:·gc.count                               thrpt   50      328.000              counts
JacksonBenchmark.tinyJsonRead:·gc.time                                thrpt   50     1162.000                  ms
JacksonBenchmark.tinyJacksonStreamWrite                               thrpt   50  3209819.503 ± 61404.246   ops/s
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate                thrpt   50     1195.117 ±    23.104  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.alloc.rate.norm           thrpt   50      410.254 ±     1.796    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space       thrpt   50     1195.699 ±    44.582  MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Eden_Space.norm  thrpt   50      410.557 ±    13.801    B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen          thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.tinyJacksonStreamWrite:·gc.churn.G1_Old_Gen.norm     thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJacksonStreamWrite:·gc.count                     thrpt   50      256.000              counts
JacksonBenchmark.tinyJacksonStreamWrite:·gc.time                      thrpt   50      924.000                  ms
JacksonBenchmark.tinyJsonWrite                                        thrpt   50  2743931.361 ± 49476.346   ops/s
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate                         thrpt   50     1282.224 ±    22.296  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.alloc.rate.norm                    thrpt   50      514.896 ±     1.920    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space                thrpt   50     1279.809 ±    58.331  MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Eden_Space.norm           thrpt   50      513.879 ±    21.435    B/op
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen                   thrpt   50       ≈ 10⁻³              MB/sec
JacksonBenchmark.tinyJsonWrite:·gc.churn.G1_Old_Gen.norm              thrpt   50       ≈ 10⁻⁴                B/op
JacksonBenchmark.tinyJsonWrite:·gc.count                              thrpt   50      274.000              counts
JacksonBenchmark.tinyJsonWrite:·gc.time                               thrpt   50      924.000                  ms

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
