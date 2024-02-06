tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":support:logging"))
    implementation(project(":core:domain"))
    implementation(project(":core:constant"))
    implementation(project(":web"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Transactional 호출용
    implementation("org.springframework:spring-tx:6.1.3")

}