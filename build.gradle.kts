plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.samio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

fun loadEnv(): Map<String, String> {
	val envFile = file("${rootProject.projectDir}/.env")
	if (!envFile.exists()) {
		throw GradleException(".env file not found")
	}

	return envFile.readLines()
		.filter { it.isNotBlank() && !it.startsWith("#") }
		.map { it.split("=", limit = 2) }
		.associate { it[0] to it.getOrElse(1) { "" } }
}

val env = loadEnv()

val githubUsername: String? = project.findProperty("githubUsername") as String? ?: System.getenv("GITHUB_USERNAME") ?: env["GITHUB_USERNAME"]
val githubToken: String? = project.findProperty("githubToken") as String? ?: System.getenv("GITHUB_TOKEN") ?: env["GITHUB_TOKEN"]

repositories {
	mavenCentral()

	maven {
		url = uri("https://maven.pkg.github.com/aronvaupel/Commons")
		credentials {
			username = githubUsername ?: throw GradleException("GitHub username not provided")
			password = githubToken ?: throw GradleException("GitHub token not provided")
		}
	}

	maven {
		url = uri("https://repo.spring.io/milestone")
	}
	maven {
		url = uri("https://repo.spring.io/snapshot")
	}

	google()
}

extra["springCloudVersion"] = "2023.0.3"

val isDevProfile: Boolean = project.hasProperty("spring.profiles.active") && project.property("spring.profiles.active") == "dev"
val isLocalProfile: Boolean = project.hasProperty("spring.profiles.active") && project.property("spring.profiles.active") == "local"

dependencies {
	implementation("com.github.aronvaupel:commons:6.4.12")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
	implementation("io.hypersistence:hypersistence-utils-hibernate-63:3.9.0")
	implementation("org.hibernate:hibernate-core:6.6.3.Final")
	implementation("org.postgresql:postgresql:42.7.2")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	if (!isDevProfile && !isLocalProfile) {
		implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	}
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.kafka:spring-kafka")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}


kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
