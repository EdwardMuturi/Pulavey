plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.mementoguy.pulavey"
        minSdk = 21
        targetSdk = 30
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding= true
    }
}

dependencies {
    with(Dependencies) {
        implementation(kotlin_ktx)
        implementation(app_compat)
        implementation(material_ui)
        implementation(constraint_layout)
        implementation(viewModel)
        implementation(viewpager2)
        implementation(koin)
        implementation(koin_work_manager)
        implementation(retrofit)
        implementation(gson)
        implementation(gson_converter)
        implementation(room)
        implementation(room_ktx)
        kapt (room_compiler)
        testImplementation(room_testing)
        testImplementation(junit)
        androidTestImplementation(junit_ext)
        androidTestImplementation(espresso)
    }
}