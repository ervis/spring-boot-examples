object Repositories {
    const val springIO = "http://repo.spring.io/libs-release"
}
object Plugins {
    const val java = "java"
    const val springBoot = "org.springframework.boot"
    const val springDependencyManagement = "io.spring.dependency-management"
    const val kotlin = "org.jetbrains.kotlin.jvm"
    const val kotlinSpring = "org.jetbrains.kotlin.plugin.spring"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
    const val kotlinNoArg = "org.jetbrains.kotlin.plugin.noarg"
    const val kotlinAllOpen = "org.jetbrains.kotlin.plugin.allopen"
    const val kotlinJpa = "org.jetbrains.kotlin.plugin.jpa"
    const val gitProperties = "com.gorylenko.gradle-git-properties"

}

object PluginVersion {
    const val kotlin = "1.3.72"
    const val springDependencyManagement = "1.0.9.RELEASE"
    const val springBoot = "2.2.5.RELEASE"
    const val gitProperties = "2.2.2"
}

object Libs {
    const val h2 = "com.h2database:h2"
    const val flyway = "org.flywaydb:flyway-core:6.3.1"
    const val sleuth = "org.springframework.cloud:spring-cloud-starter-sleuth"
    const val kotlinStdJDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlinJackson = "com.fasterxml.jackson.module:jackson-module-kotlin"
}

object TestLibs {
    const val jupyter = "org.junit.jupiter:junit-jupiter-api"
    const val jupyterEngine = "org.junit.jupiter:junit-jupiter-engine"
    const val kotlinJunit5 = "org.jetbrains.kotlin:kotlin-test-junit5"
    const val springSecurity = "org.springframework.security:spring-security-test"
    const val mockito = "org.mockito:mockito-junit-jupiter:3.2.4"
    const val hamcrest = "org.hamcrest:hamcrest:2.1"
}

object SpringBootStarter {
    const val data = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val freemarker = "org.springframework.boot:spring-boot-starter-freemarker"
    const val security = "org.springframework.boot:spring-boot-starter-security"
    const val web = "org.springframework.boot:spring-boot-starter-web"
    const val test = "org.springframework.boot:spring-boot-starter-test"
}

object SpringCloud {
    const val sleuth = "org.springframework.cloud:spring-cloud-starter-sleuth"
}

object TestContainers {
    const val postgres = "org.testcontainers:postgresql:1.13.0"
}
object MavenBom {
    const val springCloud = "org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR3"
}

object Kapt {
    const val springBootConfigurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"
}