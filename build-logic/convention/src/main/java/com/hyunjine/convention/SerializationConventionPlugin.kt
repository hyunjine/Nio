package com.hyunjine.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import kotlin.jvm.kotlin

/**
 * android module에서 사용할 api level, java version 플러그인입니다.
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