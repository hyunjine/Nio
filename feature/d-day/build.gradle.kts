plugins {
    alias(libs.plugins.feature)
}

android {
    namespace = "com.hyunjine.d_day"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)
}