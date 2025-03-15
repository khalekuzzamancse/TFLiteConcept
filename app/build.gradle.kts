plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.kzcse.tfliteconcept"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kzcse.tfliteconcept"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
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

}