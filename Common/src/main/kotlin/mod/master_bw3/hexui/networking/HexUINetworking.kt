package gay.`object`.hexdebug.networking

import dev.architectury.networking.NetworkChannel
import mod.master_bw3.hexui.HexUI
import net.minecraft.server.network.ServerPlayerEntity

object HexUINetworking {
    private val CHANNEL = NetworkChannel.create(HexUI.id("networking_channel"))

    fun init() {
        // FIXME: gross.
        CHANNEL.register(MsgEventCallbackC2S::class.java, MsgEventCallbackC2S::encode, ::MsgEventCallbackC2S, MsgEventCallbackC2S::apply)
        CHANNEL.register(MsgEventCallbackS2C::class.java, MsgEventCallbackS2C::encode, ::MsgEventCallbackS2C, MsgEventCallbackS2C::apply)
    }

    fun <T> sendToServer(message: T) = CHANNEL.sendToServer(message)

    fun <T> sendToPlayer(player: ServerPlayerEntity, message: T) = CHANNEL.sendToPlayer(player, message)
}
