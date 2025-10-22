package com.hyunjine.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * compose 라이브러리를 추가하는 플러그인입니다.
 */
internal class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")

                androidModule {
                    buildFeatures {
                        compose = true
                    }

                    dependencies {
                        implementation(libs.findBundle("compose"))
                        implementation(libs.findLibrary("androidx-activity-compose"))
                        implementation(libs.findLibrary("androidx-compose-bom"))
                        implementation(libs.findLibrary("androidx-ui"))
                        implementation(libs.findLibrary("androidx-ui-graphics"))
                        implementation(libs.findLibrary("androidx-ui-tooling"))
                        implementation(libs.findLibrary("androidx-ui-tooling-preview"))
                        androidTestImplementation(libs.findLibrary("androidx-ui-test-manifest"))
                        androidTestImplementation(libs.findLibrary("androidx-ui-test-junit4"))
                        implementation(libs.findLibrary("androidx-material3"))
                        implementation(libs.findLibrary("lifecycle.viewmodel"))
                        implementation(libs.findLibrary("lifecycle.viewmodel.compose"))
                        implementation(libs.findLibrary("hilt.navigation"))
                    }
                }
            }
        }
    }
}