plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.stonecap.wardrobe"
        versionCode = 1
        versionName = "0.0.1"

        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    hilt {
        enableAggregatingTask = true
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    compileOptions {
        // Remove after AGP 8.1.0 is stable
        // https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("DebugProbesKt.bin")
        }
    }
    namespace = "com.stonecap.wardrobe"
}

dependencies {
    implementation(projects.feature.dashboard)
    implementation(projects.feature.itemdetails)

    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.network)
    implementation(projects.core.ui)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
