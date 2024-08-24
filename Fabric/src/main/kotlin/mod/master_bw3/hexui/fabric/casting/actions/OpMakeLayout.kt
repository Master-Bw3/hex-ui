package mod.master_bw3.hexui.fabric.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.componentBuilder.container.LayoutComponentBuilder

class OpMakeLayout(private val algorithm: LayoutComponentBuilder.Algorithm) : ConstMediaAction {
    override val argc: Int
        get() = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {

        val component = LayoutComponentBuilder(algorithm)

        return listOf(ComponentIota(component))
    }
}