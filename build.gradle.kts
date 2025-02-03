plugins {
    kotlin("jvm") version "2.1.0"
}

group = "org.jluqgon214"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-kotlin-sync
    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.3.0")

    // https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-kotlin
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")

    implementation ("org.slf4j:slf4j-api:2.0.0")
    implementation ("org.slf4j:slf4j-simple:2.0.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}