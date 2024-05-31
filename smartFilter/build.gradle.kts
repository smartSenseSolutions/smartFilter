plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

buildscript {
    extra.apply {
        set("PUBLISH_GROUP_ID", "io.github.smartsensesolutions")
        set("PUBLISH_VERSION", "1.0.0")
        set("PUBLISH_ARTIFACT_ID", "SmartFilter")
        set("PUBLISH_DESCRIPTION", "An easy to customize filter view.")
        set("PUBLISH_URL", "https://github.com/smartSenseSolutions/SmartFilter")
        set("PUBLISH_LICENSE_NAME", "Apache License")
        set("PUBLISH_LICENSE_URL", "https://github.com/smartSenseSolutions/SmartFilter/blob/master/LICENSE")
        set("PUBLISH_DEVELOPER_ID", "sagarsmartsense")
        set("PUBLISH_DEVELOPER_NAME", "Mala Ruparel")
        set("PUBLISH_DEVELOPER_EMAIL", "mala.ruparel@smartsensesolutions.com")
        set("PUBLISH_SCM_CONNECTION", "scm:git:github.com/smartSenseSolutions/SmartFilter.git")
        set("PUBLISH_SCM_DEVELOPER_CONNECTION", "scm:git:ssh://github.com/SmartFilter/SmartFilter.git")
        set("PUBLISH_SCM_URL", "https://github.com/smartSenseSolutions/SmartFilter")
    }
}
apply {
    from("${rootProject.projectDir}/scripts/publish-module.gradle")
}
android {
    namespace = "com.ss.smartfilter"
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation (libs.ssp.android)
    implementation (libs.sdp.android)

}