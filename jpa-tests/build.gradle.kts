val implementation by configurations
val testImplementation by configurations
val runtimeOnly by configurations

dependencies {
    implementation(SpringBootStarter.data)
    implementation(Libs.flyway)

    testImplementation(TestContainers.postgres)

    runtimeOnly(Libs.postgres)
}