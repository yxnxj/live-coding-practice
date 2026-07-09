#!/bin/sh
set -eu

JDK21_HOME="/Users/jun/Library/Java/JavaVirtualMachines/ms-21.0.7/Contents/Home"
if [ -d "$JDK21_HOME" ]; then
  export JAVA_HOME="$JDK21_HOME"
fi

if [ -x "./gradlew" ]; then
  ./gradlew runKataTests
  exit 0
fi

if command -v gradle >/dev/null 2>&1; then
  gradle runKataTests
  exit 0
fi

if command -v kotlinc >/dev/null 2>&1; then
  rm -rf out
  mkdir -p out
  kotlinc $(find src/main/kotlin src/test/kotlin -name "*.kt") -include-runtime -d out/trip-service-tests.jar
  java -jar out/trip-service-tests.jar
  exit 0
fi

echo "Gradle or kotlinc is required to run tests from the terminal."
echo "In IntelliJ IDEA, open this solution directory as a Gradle project."
exit 1
