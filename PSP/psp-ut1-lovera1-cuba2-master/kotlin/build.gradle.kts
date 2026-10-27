plugins {
    kotlin("jvm") version "2.2.0"
}

group = "psp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "failed", "skipped") }
}

// JAR con kotlin-stdlib incluida, para poder ejecutar cualquier main con:
//   java -cp build/libs/psp-ut1-1.0-SNAPSHOT.jar psp.ut1.<paquete>.<Fichero>Kt
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
