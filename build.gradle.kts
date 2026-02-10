plugins {
    id("java")
    alias(libs.plugins.shadowJar) // ShadowJar
    alias(libs.plugins.runPaper) // Run Paper
}

group to project.property("group")

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven ("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly(libs.spigot)
    implementation(libs.miniMessage) // MiniMessage-API
}

tasks {
  runServer {
    // Configure the Minecraft version for our task.
    // This is the only required configuration besides applying the plugin.
    // Your plugin's jar (or shadowJar if present) will be used automatically.
    minecraftVersion("1.21")
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

tasks.build {
  dependsOn("shadowJar")
}

tasks.withType<JavaCompile>().configureEach {
  options.encoding = "UTF-8"
}

tasks.withType<ProcessResources>().configureEach {
    val props = mapOf(
      "name" to project.property("name"),
      "main" to project.property("main"),
      "version" to project.property("version"),
      "description" to project.property("description"),
      "author" to project.property("author"),
      "apiVersion" to project.property("apiVersion"),
    )
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
