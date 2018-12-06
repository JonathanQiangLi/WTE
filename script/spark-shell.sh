#! /bin/bash

spark-shell --conf "spark.driver.extraClassPath=./bin" --jars `ls target/scala-2.11/*assembly*.jar`
