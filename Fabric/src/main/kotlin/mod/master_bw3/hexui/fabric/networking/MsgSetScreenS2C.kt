package mod.master_bw3.hexui.fabric.networking

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.utils.putCompound
import dev.architectury.networking.NetworkManager.PacketContext
import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import java.util.function.Supplier

@JvmRecord
data class MsgSetScreenS2C(val view: ComponentBuilder<*, *>) {
    constructor(buf: PacketByteBuf) : this(ComponentBuilderType.deserialize(buf.readNbt()!!))

    fun encode(buf: PacketByteBuf) {
        buf.writeNbt(ComponentBuilderType.serialize(view))
    }
}


