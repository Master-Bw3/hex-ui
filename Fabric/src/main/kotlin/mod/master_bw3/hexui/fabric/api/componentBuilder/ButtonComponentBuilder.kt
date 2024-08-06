package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.utils.putCompound
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import dev.architectury.networking.NetworkManager
import gay.`object`.hexdebug.networking.HexUINetworking
import gay.`object`.hexdebug.networking.MsgEventCallbackC2S
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.Components
import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtOps
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.dynamic.Codecs

class ButtonComponentBuilder(
    message: Text, onClick: NbtCompound,
    children: List<ComponentBuilder<ButtonComponent, ButtonProperties>>
) : ComponentBuilder<ButtonComponent, ButtonComponentBuilder.ButtonProperties>(
    TYPE, ButtonProperties(message, onClick), children
) {
    data class ButtonProperties(val message: Text, val onClick: NbtCompound);


    override fun serialize(): NbtCompound {
        val nbt = NbtCompound();
        nbt.put("message", Codecs.TEXT.encodeStart(NbtOps.INSTANCE, properties.message).result().orElseThrow())
        nbt.put("onClick", properties.onClick)

        return nbt;
    }

    override fun build(): ButtonComponent {
        val button = Components.button(properties.message
        ) { _ -> HexUINetworking.sendToServer(MsgEventCallbackC2S(properties.onClick)) }

        return button
    }

    companion object TYPE : ComponentBuilderType<ButtonComponentBuilder>() {
        override fun deserialize(nbt: NbtCompound, world: ServerWorld): ButtonComponentBuilder {
            val message = Codecs.TEXT.decode(NbtOps.INSTANCE, nbt.get("message")).result().orElseThrow().first
            val onClick = nbt.getCompound("onClick")

            return ButtonComponentBuilder(message, onClick, listOf())
        }

    }
}