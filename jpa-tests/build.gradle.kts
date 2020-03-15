dependencies {
    implementation(Spring.data)
    implementation(Libs.flyway)

    testImplementation(TestContainers.postgres)

    runtimeOnly(Libs.h2)
}