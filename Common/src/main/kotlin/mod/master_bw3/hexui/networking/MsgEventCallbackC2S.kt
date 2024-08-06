package gay.`object`.hexdebug.networking

import dev.architectury.networking.NetworkManager.PacketContext
import mod.master_bw3.hexui.HexUI
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf

import java.util.function.Supplier

data class MsgEventCallbackC2S(private val content: NbtCompound) {
    constructor(buf: PacketByteBuf) : this(buf.readNbt()!!)

    fun encode(buf: PacketByteBuf) {
        buf.writeNbt(content)
    }

    fun apply(supplier: Supplier<PacketContext>) = supplier.get().also { ctx ->
        ctx.queue {
            HexUI.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)

        }
    }
}
