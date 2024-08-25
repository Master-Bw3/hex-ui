package mod.master_bw3.hexui.fabric.casting.actions.component

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.componentBuilder.component.ButtonComponentBuilder
import net.minecraft.text.Text
import ram.talia.moreiotas.api.getString

object OpMakeButton : ConstMediaAction {
    override val argc: Int
        get() = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val message = args.getString(0, argc)
        val onClick = args.getList(1, argc)

        val component = ButtonComponentBuilder(Text.literal(message), IotaType.serialize(ListIota(onClick)), listOf())

        return listOf(ComponentIota(component))
    }
}