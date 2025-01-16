plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
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


repositories {
	mavenCentral()

	maven {
		url = uri("https://maven.pkg.github.com/aronvaupel/Commons")
		credentials {
			username = project.findProperty("githubUsername") as String? ?: System.getenv("GITHUB_USERNAME")
			password = project.findProperty("githubToken") as String? ?: System.getenv("GITHUB_TOKEN")
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

val isLocalProfile: Boolean = project.hasProperty("spring.profiles.active") && project.property("spring.profiles.active") == "local"

dependencies {
	implementation("com.github.aronvaupel:commons:7.1.9")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("io.hypersistence:hypersistence-utils-hibernate-63:3.9.0")
	implementation("org.hibernate:hibernate-core:6.6.3.Final")
	implementation("org.postgresql:postgresql:42.7.2")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	if (!isLocalProfile) {
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
