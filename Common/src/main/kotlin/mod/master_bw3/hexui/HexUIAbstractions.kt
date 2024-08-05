@file:JvmName("HexUIAbstractions")

package mod.master_bw3.hexui

import dev.architectury.injectables.annotations.ExpectPlatform
import mod.master_bw3.hexui.registry.HexUIRegistrar

fun initRegistries(vararg registries: HexUIRegistrar<out Any>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexUIRegistrar<T>) {
    throw AssertionError()
}
