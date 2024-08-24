package mod.master_bw3.hexui.fabric.api.casting

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughArgs
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import kotlin.reflect.KClass
import kotlin.reflect.cast

fun List<Iota>.getComponent(idx: Int, argc: Int = 0): ComponentBuilder<*> {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is ComponentIota<*>) {
        return x.component
    } else {
        // TODO: I'm not sure this calculation is correct
        throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "component")
    }
}

fun <T : Any> List<Iota>.getComponent(has: KClass<T>, idx: Int, argc: Int = 0): T =
    this.getComponent(has.java, idx, argc)

fun <T : Any> List<Iota>.getComponent(has: Class<T>, idx: Int, argc: Int = 0): T {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is ComponentIota<*> && has.isInstance(x.component)) {
        return has.cast(x.component)
    } else {
        // TODO: I'm not sure this calculation is correct
        throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), has.simpleName)
    }
}