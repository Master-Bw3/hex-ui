plugins {
    id("hexui.minecraft")
}

architectury {
    common("fabric", "forge")
}

dependencies {
    implementation(libs.kotlin.stdlib)

    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation(libs.fabric.loader)
    modApi(libs.architectury)

    modApi(libs.hexcasting.common)

    modApi(libs.clothConfig.common)

    implementation(libs.mixinExtras)
}
