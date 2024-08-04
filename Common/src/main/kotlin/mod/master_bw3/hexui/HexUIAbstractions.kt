@file:JvmName("HexJsAbstractions")

package mod.master_bw3.hexui.fabric

import dev.architectury.injectables.annotations.ExpectPlatform
import mod.master_bw3.hexui.registry.HexUIRegistrar

fun initRegistries(vararg registries: HexUIRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexUIRegistrar<T>) {
    throw AssertionError()
}
