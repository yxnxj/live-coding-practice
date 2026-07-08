#!/bin/sh
set -eu

JDK21_HOME="/Users/jun/Library/Java/JavaVirtualMachines/ms-21.0.7/Contents/Home"
if [ -d "$JDK21_HOME" ]; then
  export JAVA_HOME="$JDK21_HOME"
fi

./gradlew runKataTests
