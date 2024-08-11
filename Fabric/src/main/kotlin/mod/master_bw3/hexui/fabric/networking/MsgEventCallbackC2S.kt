package mod.master_bw3.hexui.fabric.networking

import at.petrak.hexcasting.api.casting.eval.env.PackagedItemCastEnv
import at.petrak.hexcasting.api.casting.eval.vm.CastingVM
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import dev.architectury.networking.NetworkManager.PacketContext
import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.screen.HexScreenHandler
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Hand

import java.util.function.Supplier

data class MsgEventCallbackC2S(private val content: NbtCompound) {
    constructor(buf: PacketByteBuf) : this(buf.readNbt()!!)

    fun encode(buf: PacketByteBuf) {
        buf.writeNbt(content)
    }

    fun apply(supplier: Supplier<PacketContext>) = supplier.get().also { ctx ->
        ctx.queue {
            HexUI.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)

            val sPlayer: ServerPlayerEntity = ctx.player as ServerPlayerEntity
            val world = sPlayer.serverWorld
            val hex = (IotaType.deserialize(content, world)!! as ListIota).list

            val ctx = PackagedItemCastEnv(sPlayer, Hand.MAIN_HAND)
            val harness: CastingVM = CastingVM.empty(ctx)
//        harness.image = harness.image.copy(stack = listOf(model, msg))

            val clientView = harness.queueExecuteAndWrapIotas(hex.toList(), sPlayer.serverWorld)

            val result = harness.image.stack

            val handler = ((harness.env.castingEntity as? ServerPlayerEntity)
                ?.currentScreenHandler as? HexScreenHandler)

            result.forEach { handler?.update(it) }
        }
    }
}
