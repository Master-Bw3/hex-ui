package mod.master_bw3.hexui.fabric.networking

import dev.architectury.networking.NetworkChannel
import mod.master_bw3.hexui.HexUI
import net.minecraft.server.network.ServerPlayerEntity

object HexUINetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(HexUI.id("networking_channel"))

    fun init() {
        // FIXME: gross.
        CHANNEL.register(MsgEventCallbackC2S::class.java, MsgEventCallbackC2S::encode, ::MsgEventCallbackC2S, MsgEventCallbackC2S::apply)
    }

    fun <T> sendToServer(message: T) = CHANNEL.sendToServer(message)

    fun <T> sendToPlayer(player: ServerPlayerEntity, message: T) = CHANNEL.sendToPlayer(player, message)
}
