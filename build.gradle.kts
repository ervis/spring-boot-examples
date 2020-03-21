import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("http://repo.spring.io/libs-release")
        }
    }
}
plugins {
    application
    idea
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.3.61" apply false

    // Apply the application plugin to add support for building a CLI application.
    id("io.spring.dependency-management") version "1.0.6.RELEASE" apply false
    id("org.springframework.boot") version "2.2.5.RELEASE" apply false

    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.61" apply false
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.61" apply false
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.61" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.61" apply false

    kotlin("kapt") version "1.3.61" apply false
}
allprojects {
    apply(plugin = "idea")

    idea {
        module {
            inheritOutputDirs = false
            outputDir = file("$buildDir/classes/main")
            testOutputDir = file("$buildDir/classes/test")
//            outputDir = file("$buildDir/classes/kotlin/main")
//            resourceDirs.add(file("$buildDir/classes/kotlin/main"))
//
//            testOutputDir = file("$buildDir/classes/test")
//            testResourceDirs.add(file("$buildDir/classes/test"))

            isDownloadJavadoc = true
            isDownloadSources = true
        }
    }
}
subprojects {
    apply {
        plugin("java")

        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")

        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("kotlin-noarg")
        plugin("kotlin-jpa")

    }
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("http://repo.spring.io/libs-release")
        }
    }
    val implementation by configurations
    val testImplementation by configurations

    dependencies {
        // Align versions of all Kotlin components
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

        // Use the Kotlin JDK 8 standard library.
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        "kapt"("org.springframework.boot:spring-boot-configuration-processor")


        // spring-starters
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // Use the Kotlin test library.
        testImplementation("org.jetbrains.kotlin:kotlin-test")

        // Use the Kotlin JUnit integration.
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
        testRuntimeOnly("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("com.ninja-squad:springmockk:1.1.3")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
            exclude(module = "mockito-core")
        }
    }
    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }
}
application {
    // Define the main class for the application.
    mainClassName = "springboot.AppKt"
}

tasks {
    getByName<Wrapper>("wrapper") {
        gradleVersion = "6.2.2"
        distributionType = Wrapper.DistributionType.ALL
    }
}