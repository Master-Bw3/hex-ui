package mod.master_bw3.hexui.fabric.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getInt
import at.petrak.hexcasting.api.casting.iota.Iota
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.HasGap
import mod.master_bw3.hexui.fabric.componentBuilder.container.LayoutComponentBuilder

object OpSetGap : ConstMediaAction {
    override val argc: Int
        get() = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val component = args.getComponent(HasGap::class, 0, argc)
        val gap = args.getInt(1, argc)

        val newComponent = component.withGap(gap)

        return listOf(ComponentIota(newComponent))
    }
}