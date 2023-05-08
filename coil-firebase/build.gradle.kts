plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stonecap.coil_firebase"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
}

dependencies {
    val firebaseBom = platform(libs.firebase.bom)
    implementation(firebaseBom)

    implementation(libs.firebase.firestore)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}
