package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.api.casting.iota.IotaType
import com.mojang.serialization.Codec
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Component
import io.wispforest.owo.ui.core.Sizing
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.NbtTypes
import net.minecraft.text.Text

class LayoutComponentBuilder(
    algorithm: Algorithm,
    children: List<ComponentBuilder<*, *>>
) : ComponentBuilder<FlowLayout, LayoutComponentBuilder.LayoutProperties>(
    TYPE, LayoutProperties(algorithm), children
) {
    data class LayoutProperties(val algorithm: Algorithm);

    enum class Algorithm(val value: Byte, val key: String, val constructor: (Sizing, Sizing) -> FlowLayout) {

        VERTICAL(1, "vertical", Containers::verticalFlow),

        HORIZONTAL(2, "horizontal", Containers::horizontalFlow);


        companion object {
            val CODEC = Codec.BYTE.xmap(this::fromByte, Algorithm::value)

            private fun fromByte(value: Byte) = entries.first { it.value == value }
        }
    }

    override fun serialize(): NbtCompound {
        val nbt = NbtCompound();
        nbt.put("algorithm", Algorithm.CODEC.encodeStart(NbtOps.INSTANCE, properties.algorithm).result().orElseThrow())

        val childrenNbt = NbtList()
        childrenNbt.addAll(children.map { ComponentBuilderType.serialize(it) })

        nbt.put("children", childrenNbt)

        return nbt;
    }

    override fun build(eventCallbackHandler: (NbtCompound) -> Unit): FlowLayout {
        val layout =
            properties.algorithm.constructor(Sizing.fill(100), Sizing.fill(100))

        layout.children(children.map { it.build(eventCallbackHandler) })

        return layout
    }

    companion object TYPE : ComponentBuilderType<LayoutComponentBuilder>() {
        override fun deserialize(nbt: NbtCompound): LayoutComponentBuilder {
            val algorithm = Algorithm.CODEC.decode(NbtOps.INSTANCE, nbt.get("algorithm")).result().orElseThrow().first

            val children =
                nbt.getList("children", NbtElement.COMPOUND_TYPE.toInt()).map { ComponentBuilderType.deserialize(it as NbtCompound) }

            return LayoutComponentBuilder(algorithm, children)
        }

        override fun color(): Int {
            return 0xFFFFFF
        }

        override fun display(nbt: NbtCompound): Text {
            val propertiesKey = "hexui.component.algorithm.properties"

            val out = typeName().copy()
                .append("{ ")

            val data = deserialize(nbt.getCompound("data"))

            out.append(Text.translatable("${propertiesKey}.algorithm"))
                .append(": ")
                .append(data.properties.algorithm.key)

            //TODO: display children

            out.append("}")

            return out
        }

    }
}