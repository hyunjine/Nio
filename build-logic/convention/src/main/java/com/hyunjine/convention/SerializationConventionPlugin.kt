package com.hyunjine.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import kotlin.jvm.kotlin

/**
 * kotlin-serialization 라이브러리를 추가하는 플러그인입니다.
 */
internal class SerializationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.serialization")

                androidModule {
                    dependencies {
                        implementation(libs.findLibrary("kotlin.serialization"))
                    }
                }
            }
        }
    }
}