pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Labs"
include(":app")
include(":labs_basic_painting_with_views")
include(":labs_image")
include(":srs5_shuffleimage")
include(":style")
include(":app:labs_alertdialog")
include(":bottomnavigation")
include(":sharedpreferencesmodule")
include(":sqlitedatabasemodule")
include(":sqldatabesesecondmodule")
include(":viewbindingmodule")
include(":algoritmskotlinmodule")
include(":roommodulejava")
include(":advancedtodo")
include(":advancedtodo2")
include(":network_okhttp")
include(":parser")
include(":imagesparsing")
include(":login")
include(":srs1_prof")
include(":zuckerman")
include(":prof_start_apps")
