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
        set("springBootVersion", "2.4.1")
    }

    val springBootVersion = rootProject.extra["springBootVersion"]

    project.extra["springbootweb"] = "org.springframework.boot:spring-boot-starter-web:${springBootVersion}"
    project.extra["springdata"]     = "org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}"
    project.extra["inject"] = "javax.inject:javax.inject:1"

}

dependencies {

    implementation(project.extra["springbootweb"] as String)

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
