// Top-level build file where you can add configuration options common to all sub-projects/modules.
import java.io.FileInputStream
import java.util.Properties


buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.dagger.hilt) apply false
}

val credentialDp = loadCredentialData()

extra.apply {
    set("baseURL", credentialDp.getProperty("BASE_URL"))
    set("apiKey", credentialDp.getProperty("API_KEY"))
}

fun loadCredentialData(): Properties {
    val keysFile = file("credentials.properties")
    val keysProperties = Properties()
    keysProperties.load(FileInputStream(keysFile))
    return keysProperties
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        if (project.findProperty("composeCompilerReports") == "true") {
            freeCompilerArgs += listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler"
            )
        }
        if (project.findProperty("composeCompilerMetrics") == "true") {
            freeCompilerArgs += listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_compiler"
            )
        }
    }
}