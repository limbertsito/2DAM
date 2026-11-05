// build.gradle.kts · Construcción del proyecto: Kotlin/JVM con JDK 21
plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.coroutines)
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

// Programa que lanza «./gradlew run»; se elige con -Pprograma=paquete.ClaseKt
application {
    mainClass = providers.gradleProperty("programa").orElse("com.iesvb.psp.ut2.MainKt")
}

// Con -Pesperar, el programa recibe -Desperar: B3.2 lo usa para seguir vivo y hacer el volcado de hilos
tasks.named<JavaExec>("run") {
    if (providers.gradleProperty("esperar").isPresent) systemProperty("esperar", "1")
}

tasks.test {
    useJUnitPlatform()
}
