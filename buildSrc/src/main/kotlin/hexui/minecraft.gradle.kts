// A convention plugin that should be applied to all Minecraft-related subprojects, including Common.

package hexui

import kotlin.io.path.div

plugins {
    id("hexui.java")
    id("hexui.utils.json5")

    `maven-publish`
    id("dev.architectury.loom")
}

val modId: String by project
val platform: String by project

base.archivesName = "${modId}-$platform"

loom {
    silentMojangMappingsLicense()
    accessWidenerPath = project(":Common").file("src/main/resources/hexui.accesswidener")
}

dependencies {
    minecraft(libs.minecraft)

    mappings(variantOf(libs.yarn) { classifier("v2") })

    annotationProcessor(libs.bundles.asm)
}

sourceSets {
    main {
        kotlin {
            srcDir(file("src/main/java"))
        }
        resources {
            srcDir(file("src/generated/resources"))
        }
    }
}

tasks {
    val artifactsTask = register<Copy>("githubArtifacts") {
        from(remapJar, remapSourcesJar, get("javadocJar"))
        into(rootDir.toPath() / "build" / "githubArtifacts")
    }

    build {
        dependsOn(artifactsTask)
    }
}
