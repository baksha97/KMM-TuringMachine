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

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:")
    implementation("androidx.navigation:navigation-safe-args-generator:2.3.5") {
        exclude(module="xpp3")
    }

    val nav_version = "2.3.5"

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")

    val accVersion = "0.19.0"
    implementation("com.google.accompanist:accompanist-insets:$accVersion")
    implementation("androidx.compose.material:material-icons-extended:1.0.3")

    implementation ("com.jakewharton.timber:timber:5.0.1")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.baksha97.turingmachineapp.android"
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