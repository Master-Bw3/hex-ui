package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.utils.putCompound
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import io.wispforest.owo.ui.component.ButtonComponent
import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtOps
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.dynamic.Codecs

class ButtonComponentBuilder(
    message: Text, onClick: ListIota,
    children: List<ComponentBuilder<ButtonComponent, ButtonProperties>>
) : ComponentBuilder<ButtonComponent, ButtonComponentBuilder.ButtonProperties>(
    TYPE, ButtonProperties(message, onClick), children
) {
    data class ButtonProperties(val message: Text, val onClick: ListIota);


    override fun serialize(): NbtCompound {
        val nbt = NbtCompound();
        nbt.put("message", Codecs.TEXT.encodeStart(NbtOps.INSTANCE, properties.message).result().orElseThrow())
        nbt.put("onClick", properties.onClick.serialize())

        return nbt;
    }

    companion object TYPE : ComponentBuilderType<ButtonComponentBuilder>() {
        override fun deserialize(nbt: NbtCompound, world: ServerWorld): ButtonComponentBuilder {
            val message = Codecs.TEXT.decode(NbtOps.INSTANCE, nbt.get("message")).result().orElseThrow().first
            val onClick = HexIotaTypes.LIST.deserialize(nbt.getCompound("onClick"), world)?: ListIota(listOf())

            return ButtonComponentBuilder(message, onClick, listOf())
        }

    }
}