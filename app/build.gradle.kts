// import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.matrimony.rvrmatrimony"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.matrimony.rvrmatrimony"
        minSdk = 25
        targetSdk = 34
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
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // ssp sdp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    // for Google Sign-in
    implementation("com.google.android.gms:play-services-auth:20.4.0")
    implementation("com.google.firebase:firebase-auth:22.1.1")

    // PinView
    implementation("io.github.chaosleung:pinview:1.4.4")

    // BubbleTabBar
    implementation("io.ak1:bubbletabbar:1.0.8")

    // circle image view
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.0")
    implementation("com.google.android.material:material:1.9.0")

    // glide images
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Dagger
    val daggerVersion = "2.51.1"
    implementation("com.google.dagger:dagger:${daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${daggerVersion}")

    // Hilt
    val hiltVersion = "2.51.1"
    implementation("com.google.dagger:hilt-android:${hiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${hiltVersion}")
    // kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    // ROOM Database
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:${roomVersion}")
    annotationProcessor("androidx.room:room-compiler:${roomVersion}")
    kapt("androidx.room:room-compiler:$roomVersion")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${roomVersion}")

    // security - shared Prefs
    implementation("androidx.security:security-crypto:1.1.0-alpha03")

    // flexbox layout
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    // Gson
    implementation("com.google.code.gson:gson:2.11.0")

    // Retrofit and OkHttp
    implementation("com.squareup.okio:okio:3.1.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // RazorPay Payment Integration
    implementation("com.razorpay:checkout:1.6.40")

//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")
    // implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Following are Jetpack Compose Dependencies
    // CAUTION: PLEASE DON'T EDIT THE VERSION VARIABLE BELOW EVEN IF ANDROID STUDIO
    // SUGGESTS SINCE IT HAS BEEN SET AS PER THE COMPOSE-KOTLIN COMPATIBILITY LIST
    val composeUiVersion = "1.5.2"

    // Compose core libraries
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.material:material:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")

    // Optional - for ViewModel support with Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Optional - for Navigation support with Compose
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Optional - for Activity support with Compose
    implementation("androidx.activity:activity-compose:1.8.2")

    // Others
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.ui:ui-graphics")

    // Debugging tools
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
}