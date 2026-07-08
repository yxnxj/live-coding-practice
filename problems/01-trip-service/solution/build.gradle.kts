plugins {
    kotlin("jvm") version "2.0.21"
}

kotlin {
    jvmToolchain(21)
}

tasks.register<JavaExec>("runKataTests") {
    group = "verification"
    description = "Runs the lightweight Trip Service kata test runner."

    dependsOn("testClasses")
    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("tripservicekata.TripServiceTestKt")
}
