@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"${rootProject.extra["apiKey"]}\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"${rootProject.extra["apiKey"]}\"")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Network
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)

    //Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //Moshi
    implementation(libs.retrofit.moshi)
    implementation(libs.moshi)
    ksp(libs.moshiCodeGen)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)

    //DataStore
    implementation(libs.datastore.preferences)

    implementation(libs.timber)

    testImplementation(libs.mockk)
    testImplementation(libs.mockk.agentJvm)
    androidTestImplementation(libs.mockk.android)

    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.unit.truth)
    testImplementation(libs.unit.mockito)
}