import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}


dependencies {

    // spring
    implementation(project.extra["springbootweb"] as String)

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
