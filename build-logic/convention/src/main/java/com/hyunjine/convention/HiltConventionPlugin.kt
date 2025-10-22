package com.hyunjine.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * hilt 라이브러리를 추가하는 플러그인입니다.
 */
internal class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("com.google.devtools.ksp")

                androidModule {
                    dependencies {
                        implementation(libs.findLibrary("hilt.android"))
                        ksp(libs.findLibrary("hilt.compiler"))
                    }
                }
            }
        }
    }
}