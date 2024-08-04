@file:JvmName("HexUIAbstractionsImpl")

package mod.master_bw3.hexui.fabric

import mod.master_bw3.hexui.registry.HexDebugRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexDebugRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
