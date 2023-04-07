plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.dependencyUpdate)
}

tasks {
    dependencyUpdates {
        rejectVersionIf {
            !isStableVersion(candidate.version) && isStableVersion(currentVersion)
        }
    }
}

fun isStableVersion(version: String): Boolean {
    val stableKeywords = listOf("RELEASE", "FINAL", "GA")
    val explicitlyStable = stableKeywords.any { version.uppercase().contains(it) }

    val regex = Regex("^[0-9,.v]+(-rc\\d*)?$")

    return explicitlyStable || regex.matches(version)
}
