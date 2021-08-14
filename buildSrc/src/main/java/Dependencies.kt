/**
 * Created by Edward Muturi on 22/04/2021.
 */
object Versions {
    const val kotlinKtxVersion = "1.3.2"
    const val appCompat = "1.2.0"
    const val materialLib = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val junitVersion = "4.13.2"
    const val junitExtVersion = "1.1.2"
    const val espressoVersion = "3.3.0"
    const val viewPager2Version= "1.0.0"
    const val koin_version= "3.1.2"
    const val retrofit_version= "2.9.0"
    const val gson_version= "2.8.6"
    const val lifecycle_version = "2.3.1"
    const val okhttp_version= "4.3.1"
    const val room_version = "2.3.0"
    const val androidXFragmentVersion= "1.3.6"
}

object Dependencies {
    const val kotlin_ktx = "androidx.core:core-ktx:${Versions.kotlinKtxVersion}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material_ui = "com.google.android.material:material:${Versions.materialLib}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val viewpager2= "androidx.viewpager2:viewpager2:${Versions.viewPager2Version}"
    const val koin= "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koin_work_manager= "io.insert-koin:koin-androidx-workmanager:${Versions.koin_version}"
    const val retrofit= "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val gson_converter= "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val gson= "com.google.code.gson:gson:${Versions.gson_version}"
    const val viewModel= "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_compiler= "androidx.room:room-compiler:${Versions.room_version}"
    const val room_ktx= "androidx.room:room-ktx:${Versions.room_version}"
    const val room_testing= "androidx.room:room-testing:${Versions.room_version}"
    const val androidX_fragment= "androidx.fragment:fragment-ktx:${Versions.androidXFragmentVersion}"

}