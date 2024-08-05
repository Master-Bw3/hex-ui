@file:JvmName("HexUIAbstractionsImpl")

package mod.master_bw3.hexui.fabric

import mod.master_bw3.hexui.registry.HexUIRegistrar
import net.minecraft.registry.Registry

fun <T : Any> initRegistry(registrar: HexUIRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
