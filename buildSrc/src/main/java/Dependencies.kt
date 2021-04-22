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
}

object Dependencies {
    const val kotlin_ktx = "androidx.core:core-ktx:${Versions.kotlinKtxVersion}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material_ui = "com.google.android.material:material:${Versions.materialLib}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}