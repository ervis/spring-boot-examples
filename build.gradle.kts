import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri(Repositories.springIO)
        }
    }
}
plugins {
    application
    idea
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id(Plugins.kotlin) version PluginVersion.kotlin apply false

    // Apply the application plugin to add support for building a CLI application.
    id(Plugins.springDependencyManagement) version PluginVersion.springDependencyManagement apply false
    id(Plugins.springBoot) version PluginVersion.springBoot apply false

    id(Plugins.kotlinAllOpen) version PluginVersion.kotlin apply false
    id(Plugins.kotlinNoArg) version PluginVersion.kotlin apply false
    id(Plugins.kotlinJpa) version PluginVersion.kotlin apply false
    id(Plugins.kotlinSpring) version PluginVersion.kotlin apply false

    id(Plugins.kotlinKapt) version PluginVersion.kotlin apply false
}

allprojects {
    apply(plugin = "idea")

    idea {
        module {
            inheritOutputDirs = false
            outputDir = file("$buildDir/classes/main")
            testOutputDir = file("$buildDir/classes/test")

            isDownloadJavadoc = true
            isDownloadSources = true
        }
    }
}
subprojects {
    apply {
        plugin(Plugins.java)

        plugin(Plugins.springBoot)
        plugin(Plugins.springDependencyManagement)

        plugin(Plugins.kotlin)
        plugin(Plugins.kotlinSpring)
        plugin(Plugins.kotlinKapt)
        plugin(Plugins.kotlinNoArg)
        plugin(Plugins.kotlinJpa)

    }
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri(Repositories.springIO)
        }
    }
    val implementation by configurations
    val testImplementation by configurations
    val compileOnly by configurations
    val annotationProcessor by configurations
    val testCompileOnly by configurations
    val testAnnotationProcessor by configurations

    dependencies {
        // Align versions of all Kotlin components
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

        // Use the Kotlin JDK 8 standard library.
        implementation(Libs.kotlinStdJDK8)
        implementation(Libs.kotlinReflect)
        implementation(Libs.kotlinJackson)


        // spring-starters
        implementation(SpringBootStarter.web)
        implementation(SpringCloud.sleuth)
        "kapt"(Kapt.springBootConfigurationProcessor)

        // Use the Kotlin test library.
        testImplementation("org.jetbrains.kotlin:kotlin-test")

        // Use the Kotlin JUnit integration.
        testImplementation(TestLibs.jupyter)
        testRuntimeOnly(TestLibs.jupyterEngine)
        testRuntimeOnly(TestLibs.kotlinJunit5)
        testImplementation("com.ninja-squad:springmockk:1.1.3")
        testImplementation(SpringBootStarter.test) {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
            exclude(module = "mockito-core")
        }
        // security in tests
        implementation(TestLibs.springSecurity)
        testImplementation(TestLibs.mockito)
        testImplementation(TestLibs.hamcrest)
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom(MavenBom.springCloud)
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
        gradleVersion = "6.5.1"
        distributionType = Wrapper.DistributionType.ALL
    }
}
