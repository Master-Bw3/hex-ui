package mod.master_bw3.hexui.fabric.casting.actions.component

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.getPositiveInt
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import mod.master_bw3.hexui.fabric.api.casting.getSizing
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.componentBuilder.component.ButtonComponentBuilder
import mod.master_bw3.hexui.fabric.componentBuilder.container.GridLayoutComponentBuilder
import net.minecraft.text.Text
import ram.talia.moreiotas.api.getString

object OpMakeGridLayout : ConstMediaAction {
    override val argc: Int
        get() = 4

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val verticalSizing = args.getSizing(0, argc)
        val horizontalSizing = args.getSizing(0, argc)
        val rows = args.getPositiveInt(0, argc)
        val columns = args.getPositiveInt(0, argc)

        val component = GridLayoutComponentBuilder(
            verticalSizing,
            horizontalSizing,
            rows,
            columns,
        )

        return listOf(ComponentIota(component))
    }
}