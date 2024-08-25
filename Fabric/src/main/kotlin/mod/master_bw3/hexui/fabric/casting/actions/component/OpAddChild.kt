package mod.master_bw3.hexui.fabric.casting.actions.component

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.HasChildren

object OpAddChild : ConstMediaAction {

    override val argc: Int = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val component = args.getComponent(HasChildren::class, 0, argc)
        val child = args.getComponent(1, argc)

        return listOf(ComponentIota(component.addChild(child)))
    }
}