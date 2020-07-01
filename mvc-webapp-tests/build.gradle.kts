val implementation by configurations

dependencies {
    implementation(SpringBootStarter.freemarker)
    implementation(SpringBootStarter.security)
    implementation(SpringBootStarter.web)
}
configure<com.gorylenko.GitPropertiesPluginExtension> {
    gitPropertiesName = "git.properties"
    gitPropertiesResourceDir = "$buildDir/classes/java/main/resources"
}
