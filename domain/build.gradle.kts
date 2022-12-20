import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}


dependencies {

    implementation(project.extra["inject"] as String)

    // test
    testImplementation(project.extra["test-junit-api"] as String)
    testRuntimeOnly(project.extra["test-junit-engine"] as String)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}
