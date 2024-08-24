package mod.master_bw3.hexui.fabric.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.HasChildren

object OpSetChildren : ConstMediaAction {

    override val argc: Int = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val component = args.getComponent(HasChildren::class, 0, argc)
        val list = args.getList(1, argc)

        val children = list.map {
            val componentIota = it as? ComponentIota<*> ?: throw MishapInvalidIota.ofType(it, 1, "component")
            componentIota.component
        }

        return listOf(ComponentIota(component.withChildren(children)))
    }
}