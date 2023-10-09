import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm") version "1.7.21"
    application
    jacoco
}

application {
    mainClass.set("MainKt")
}

group = "com.julianasaran.blog"
version = "0.0.1"

repositories {
    mavenCentral()
}

val coroutines: String by project
val http4k: String by project
val mockk: String by project
val logback: String by project
val logstashVersion: String by project
val microutils: String by project
val jacksonVersion: String by project
val ormVersion: String by project
val mysqlConnectorVersion: String by project

dependencies {
    implementation(kotlin("stdlib"))

    implementation(platform("org.http4k:http4k-bom:$http4k"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation("io.github.microutils:kotlin-logging-jvm:$microutils")
    implementation("ch.qos.logback:logback-core:$logback")
    implementation("ch.qos.logback:logback-classic:$logback")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashVersion")

    implementation("io.tcds:orm:$ormVersion")
    implementation("mysql:mysql-connector-java:$mysqlConnectorVersion") // 5.1.13

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:$mockk")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("started", "skipped", "passed", "failed")

        showCauses = true
        showStackTraces = true
        showExceptions = true
        exceptionFormat = TestExceptionFormat.FULL
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }
tasks.withType<Tar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }
tasks.withType<Zip> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }

jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}
