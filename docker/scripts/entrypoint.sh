#!/bin/bash
set -e

java -jar /app/benchmarks.jar \
    -jvmArgsAppend="-XX:+UseG1GC -server -Xmx4096m -Xms4096m" \
    -prof gc
    ${JMH_PARAMS}
