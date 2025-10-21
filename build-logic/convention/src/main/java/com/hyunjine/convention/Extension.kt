package com.hyunjine.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.hyunjine.convention.implementation
import com.hyunjine.convention.libs
import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import java.util.Optional

internal fun DependencyHandlerScope.implementation(path: Optional<*>) {
    add("implementation", path.get())
}

internal fun DependencyHandlerScope.implementation(path: ProjectDependency) {
    add("implementation", path)
}

internal fun DependencyHandlerScope.compileOnly(path: ProjectDependency) {
    add("compileOnly", path)
}

internal fun DependencyHandlerScope.testImplementation(path: Optional<*>) {
    add("testImplementation", path.get())
}

internal fun DependencyHandlerScope.androidTestImplementation(path: Optional<*>) {
    add("androidTestImplementation", path.get())
}

internal fun DependencyHandlerScope.ksp(path: Optional<*>) {
    add("ksp", path.get())
}

internal fun DependencyHandlerScope.ksp(path: ProjectDependency) {
    add("ksp", path)
}

internal fun DependencyHandlerScope.kapt(path: Optional<*>) {
    add("kapt", path.get())
}

private fun CommonExtension<*, *, *, *, *, *>.kotlin(block: KotlinAndroidProjectExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlin", block)
}

internal fun CommonExtension<*, *, *, *, *, *>.kotlinCompileOptions(block: KotlinJvmCompilerOptions.() -> Unit) {
    kotlin {
        block(compilerOptions)
    }
}

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.androidModule(block: CommonExtension<*, *, *, *, *, *>.() -> Unit) {
    with(pluginManager) {
        withPlugin("com.android.application") {
            configure<ApplicationExtension>(project = this@androidModule, block = block)
        }

        withPlugin("com.android.library") {
            configure<LibraryExtension>(project = this@androidModule, block = block)
        }
    }
}

internal inline fun <reified T: CommonExtension<*, *, *, *, *, *>> configure(project: Project, crossinline block: T.() -> Unit) {
    with(project) {
        extensions.configure<T> {
            block.invoke(this)
        }
    }
}