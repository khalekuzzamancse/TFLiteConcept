plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kzcse.tfliteconcept"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.kzcse.tfliteconcept"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // This block is different from the one you use to link Gradle
        // to your CMake or ndk-build script.
        externalNativeBuild {
            // For ndk-build, instead use the ndkBuild block.
            cmake {
                // Passes optional arguments to CMake.
                arguments += listOf("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            }
        }
    }
    ndkVersion = "28.0.12433566" // example: r28 version number
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //noinspection UseTomlInstead
    implementation("com.github.SmartToolFactory:Compose-Cropper:0.5.0")
//    implementation("com.github.Tanish-Ranjan:crop-kit:1.0.0")
    //noinspection UseTomlInstead
    implementation("androidx.navigation:navigation-compose:2.9.0-alpha08")
    //noinspection UseTomlInstead
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    //

    //noinspection UseTomlInstead
    implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
    //noinspection UseTomlInstead
    implementation("org.tensorflow:tensorflow-lite:2.9.0")
    //noinspection UseTomlInstead// Core TensorFlow Lite
    implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
    //noinspection UseTomlInstead// For vision tasks
    implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")         // GPU acceleration (optional for performance)
    implementation(libs.bundles.navigation3)
}