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
    implementation(project.extra["hibernate-validator"]  as String)

    // kotlin
    implementation(project.extra["jackson-kotlin"] as String)
    implementation(kotlin("stdlib-jdk8"))

    // test
    implementation(project.extra["spring-boot-test"] as String)
    implementation(project.extra["h2"] as String)
    testImplementation(project.extra["test-junit-api"] as String)
    testRuntimeOnly(project.extra["test-junit-engine"] as String)
    implementation("com.marcinziolo:kotlin-wiremock:2.0.1")

    // modules
    implementation(project(":domain"))
    implementation(project(":application"))

    // libs
    implementation("io.jsonwebtoken:jjwt:0.9.1")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}