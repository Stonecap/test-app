pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Wardrobe"
include(":app")
include(":core:data")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":core:firestore")
include(":feature:dashboard")
include(":feature:auth")
include(":feature:closet")
include(":feature:itemdetails")
include(":coil-firebase")
