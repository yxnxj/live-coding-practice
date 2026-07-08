plugins {
    kotlin("jvm") version "2.0.21"
}

kotlin {
    jvmToolchain(21)
}

tasks.register<JavaExec>("runKataTests") {
    group = "verification"
    description = "Runs the lightweight kata test runner."

    dependsOn("testClasses")
    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("args.ArgsTestKt")
}
