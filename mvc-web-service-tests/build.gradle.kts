
val implementation by configurations

dependencies {
    implementation(SpringBootStarter.security)
    implementation(SpringBootStarter.web)

    implementation(Libs.sleuth)
}