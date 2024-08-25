package mod.master_bw3.hexui.fabric.casting.actions.sizing

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getDouble
import at.petrak.hexcasting.api.casting.iota.Iota
import mod.master_bw3.hexui.fabric.api.casting.SizingData
import mod.master_bw3.hexui.fabric.api.casting.iota.SizingIota
import kotlin.math.roundToInt

object OpMakeFillSizing : ConstMediaAction {
    override val argc: Int
        get() = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val fill = args.getDouble(0, argc)

        val sizing = SizingData.Fill(fill.roundToInt())

        return listOf(SizingIota(sizing))
    }
}