package mod.master_bw3.hexui.fabric.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.ButtonComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.LayoutComponentBuilder
import net.minecraft.text.Text
import ram.talia.moreiotas.api.getString
import java.util.ArrayList

class OpMakeLayout(private val algorithm: LayoutComponentBuilder.Algorithm) : ConstMediaAction {
    override val argc: Int
        get() = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val list = args.getList(0, argc)

        val children = list.map {
            val componentIota = it as? ComponentIota<*> ?: throw MishapInvalidIota.ofType(it, 1, "component")
            componentIota.component
        }


        val component = LayoutComponentBuilder(algorithm, children)

        return listOf(ComponentIota(component))
    }
}