package mod.master_bw3.hexui.fabric.componentBuilder.component

import at.petrak.hexcasting.api.casting.iota.IotaType
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.Components
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtOps
import net.minecraft.text.Text
import net.minecraft.util.dynamic.Codecs

data class ButtonComponentBuilder(
    val message: Text, val onClick: NbtCompound,
    val children: List<ComponentBuilder<ButtonComponent>>
) : ComponentBuilder<ButtonComponent>(TYPE) {

    override fun serialize(): NbtCompound {
        val nbt = NbtCompound();
        nbt.put("message", Codecs.TEXT.encodeStart(NbtOps.INSTANCE, message).result().orElseThrow())
        nbt.put("onClick", onClick)

        return nbt;
    }

    override fun build(eventCallbackHandler: (NbtCompound) -> Unit): ButtonComponent {
        val button = Components.button(
            message
        ) { _ -> eventCallbackHandler(onClick) }

        return button
    }

    companion object TYPE : ComponentBuilderType<ButtonComponentBuilder>() {
        override fun deserialize(nbt: NbtCompound): ButtonComponentBuilder {
            val message = Codecs.TEXT.decode(NbtOps.INSTANCE, nbt.get("message")).result().orElseThrow().first
            val onClick = nbt.getCompound("onClick")

            return ButtonComponentBuilder(message, onClick, listOf())
        }

        override fun color(): Int {
            return 0xFFFFFF
        }

        override fun display(nbt: NbtCompound): Text {
            val propertiesKey = "hexui.component.button.properties"

            val out = typeName().copy()
                .append("{ ")

            val data = deserialize(nbt.getCompound("data"))

            out.append(Text.translatable("${propertiesKey}.message"))
                .append(": \"")
                .append(data.message)
                .append("\", ")

            out.append(Text.translatable("${propertiesKey}.onClick"))
                .append(": ")
                .append(IotaType.getDisplay(data.onClick))

            out.append("}")

            return out
        }

    }
}