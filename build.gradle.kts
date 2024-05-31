// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}
apply {
    from("${rootDir}/scripts/publish-root.gradle")
}

