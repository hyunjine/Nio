plugins {
    alias(libs.plugins.feature)
    id("kotlin-parcelize")
}

android {
    namespace = "com.hyunjine.timer"
}

dependencies {
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.kotlinx.coroutines.rx3)
    implementation(libs.androidx.lifecycle.service)
}