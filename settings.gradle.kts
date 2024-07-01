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
        maven(url = "https://dl.bintray.com/inmobi/maven")

    }
}

rootProject.name = "MagicbidSDKLite"
include(":app")
include(":mylibrary")
