import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.springframework.boot") version "2.4.1"
    application
}

java.sourceCompatibility = JavaVersion.VERSION_11


allprojects {

    group = "com.app"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
    }

    ext {
        set("springBootVersion", "2.7.5")
    }

    val springBootVersion = rootProject.extra["springBootVersion"]

    // springboot
    project.extra["springbootweb"] = "org.springframework.boot:spring-boot-starter-web:${springBootVersion}"
    project.extra["springdata"]     = "org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}"
    project.extra["spring-cloud-started-config"] = "org.springframework.cloud:spring-cloud-starter-config:3.1.4"
    project.extra["spring-cloud-started-bootstrap"] = "org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.4"
    project.extra["jackson-kotlin"] = "com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3"
    project.extra["feign-starter"] = "org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5"

    // java
    project.extra["inject"] = "javax.inject:javax.inject:1"

}

dependencies {

    implementation(project.extra["springbootweb"] as String)
    implementation(project.extra["feign-starter"] as String)

    implementation(project(":infrastructure"))

}

application {
    mainClass.set("com.app.Application")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}
