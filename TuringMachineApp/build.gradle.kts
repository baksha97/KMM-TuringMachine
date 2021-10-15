plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
//    implementation("com.android.tools.build:gradle:7.0.0-alpha11")

    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.3.1")
    // Compose Material Design
    implementation ("androidx.compose.material:material:1.0.1")
    // Animations
    implementation ("androidx.compose.animation:animation:1.0.1")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui-tooling:1.0.1")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    implementation ("androidx.compose.compiler:compiler:1.0.0-beta08")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.example.turingmachineapp.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    // Set both the Java and Kotlin compilers to target Java 8.
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
                targetCompatibility =JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerVersion = "1.5.31"
        kotlinCompilerExtensionVersion = "1.1.0-alpha06"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}