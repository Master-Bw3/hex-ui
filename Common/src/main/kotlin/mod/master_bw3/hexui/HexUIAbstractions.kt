@file:JvmName("HexUIAbstractions")

package mod.master_bw3.hexui.fabric

import dev.architectury.injectables.annotations.ExpectPlatform
import mod.master_bw3.hexui.registry.HexUIRegistrar

@ExpectPlatform
fun initRegistries(vararg registries: HexUIRegistrar<*>) {
    throw AssertionError()
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexUIRegistrar<T>) {
    throw AssertionError()
}
