package mod.master_bw3.hexui.fabric.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import gay.`object`.hexdebug.networking.HexUINetworking
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.networking.MsgSetScreenS2C
import net.minecraft.server.network.ServerPlayerEntity

object OpDisplayScreen : ConstMediaAction {
    override val argc: Int
        get() = 3

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val init = args[0]
        val update = args.getList(1, argc)
        val view = args.getComponent(2, argc)

        val caster = env.castingEntity
        if (caster is ServerPlayerEntity) {
            HexUINetworking.sendToPlayer(
                caster,
                MsgSetScreenS2C(view)
            )
        }

        return listOf()
    }
}