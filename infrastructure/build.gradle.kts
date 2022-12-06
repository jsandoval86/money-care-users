import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}


dependencies {

    // spring
    implementation(project.extra["springbootweb"] as String)
    implementation(project.extra["spring-cloud-started-config"] as String)
    implementation(project.extra["spring-cloud-started-bootstrap"] as String)
    implementation(project.extra["feign-starter"] as String)

    // kotlin
    implementation(project.extra["jackson-kotlin"] as String)

    // modules
    implementation(project(":domain"))
    implementation(project(":application"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}
