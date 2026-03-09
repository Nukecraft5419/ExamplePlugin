plugins {
    id("java")
    alias(libs.plugins.shadowJar) // ShadowJar
    alias(libs.plugins.runPaper) // Run Paper
}

group = providers.gradleProperty("group").get()
version = providers.gradleProperty("version").get()

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://repo.extendedclip.com/releases/") {
        name = "placeholder-api"
    }
    maven("https://repo.nukecraft5419.com/")
}

dependencies {
    compileOnly(libs.spigot) // Spigot API
    compileOnly(libs.placeholderApi) // Placeholder API
    compileOnly(libs.adventureBukkit) // Adventure Platform Bukkit
    compileOnly(libs.miniMessage) // MiniMessage API

    implementation(libs.nukeLexicon) // NukeLexicon
    implementation(libs.bStats) // bStats API
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

tasks.jar {
  enabled = false
}

tasks.shadowJar {
  // Instructs ShadowJar to bundle all dependencies marked as "implementation"
  // (like bStats) into our final plugin JAR.
  configurations = listOf(project.configurations.runtimeClasspath.get())

  // Removes the default "-all" suffix from the generated JAR file name,
  // keeping the output name clean (e.g., ExamplePlugin-1.0.0.jar).
  archiveClassifier.set("")

  // Relocation: Moves external libraries into our plugin's internal package structure.
  // This is CRITICAL to prevent ClassNotFoundException or NoSuchMethodError conflicts
  // if another plugin on the same server is using a different version of bStats.
  relocate("dev.nukecraft5419.nukelexicon", "${project.group}.libs.nukelexicon")
  relocate("org.bstats", "${project.group}.libs.bStats")
}

tasks.build {
  dependsOn("shadowJar")
}

tasks.withType<JavaCompile>().configureEach {
  options.encoding = "UTF-8"
}

tasks.withType<ProcessResources>().configureEach {
    val props = mapOf(
      "name" to providers.gradleProperty("name").get(),
      "main" to providers.gradleProperty("main").get(),
      "version" to providers.gradleProperty("version").get(),
      "description" to providers.gradleProperty("description").get(),
      "author" to providers.gradleProperty("author").get(),
      "apiVersion" to providers.gradleProperty("apiVersion").get(),
      "adventure" to libs.adventureBukkit.get(),
      "miniMessage" to libs.miniMessage.get(),
    )

    inputs.properties(props)
    filteringCharset = "UTF-8"

    filesMatching("plugin.yml") {
        expand(props)
    }
}
