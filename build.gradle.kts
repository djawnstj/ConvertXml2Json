import org.gradle.internal.classpath.Instrumented.systemProperty

plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version("7.1.1")
}

group = "com.djawnstj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

application {
    mainClass.set("com.djawnstj.Main")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "com.djawnstj.Main")

    }
}

tasks.shadowJar {
    archiveBaseName.set("ConvertXml2Json")
    archiveClassifier.set("")
    archiveVersion.set("1.0-SNAPSHOT")

    manifest {
        attributes(mapOf(
            "Main-Class" to application.mainClass.get(),
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Multi-Release" to "true",
            "Add-Opens" to "java.base/java.lang java.base/java.io",
            "Add-Exports" to "java.base/sun.nio.ch",
            "JVM-Args" to "-Dfile.encoding=UTF-8",
        ))
    }

    mergeServiceFiles()
    exclude("META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.SF")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Dfile.encoding=UTF-8")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs = listOf("-Xlint:unchecked", "-Xlint:deprecation", "-Xlint:-options")
}

tasks.withType<Wrapper> {
    systemProperty("file.encoding", "UTF-8")
}

tasks.test {
    useJUnitPlatform()
}
