package mod.master_bw3.hexui.fabric.componentBuilder.container

import com.mojang.serialization.Codec
import com.mojang.serialization.OptionalDynamic
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.Sizing
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtOps
import net.minecraft.text.Text
import java.util.*

data class LayoutComponentBuilder(
    val algorithm: Algorithm,
    val children: List<ComponentBuilder<*>>,
    val gap: Int = 0,
    ) : ComponentBuilder<FlowLayout>(TYPE) {

    enum class Algorithm(val value: Byte, val key: String, val constructor: (Sizing, Sizing) -> FlowLayout) {

        VERTICAL(1, "vertical", Containers::verticalFlow),

        HORIZONTAL(2, "horizontal", Containers::horizontalFlow),

        LTR_TEXT(3, "ltrText", Containers::ltrTextFlow);

        companion object {
            val CODEC = Codec.BYTE.xmap(this::fromByte, Algorithm::value)

            private fun fromByte(value: Byte) = entries.first { it.value == value }
        }
    }

    override fun serialize(): NbtCompound {
        val nbt = NbtCompound();
        nbt.put("algorithm", Algorithm.CODEC.encodeStart(NbtOps.INSTANCE, algorithm).result().orElseThrow())

        val childrenNbt = NbtList()
        childrenNbt.addAll(children.map { ComponentBuilderType.serialize(it) })

        nbt.put("children", childrenNbt)

        return nbt;
    }

    override fun build(eventCallbackHandler: (NbtCompound) -> Unit): FlowLayout {
        val layout =
            algorithm.constructor(Sizing.fill(100), Sizing.fill(100))

        layout.children(children.map { it.build(eventCallbackHandler) })

        return layout
    }

    fun withGap(gap: Int) = copy(gap = gap)

    fun withAlgorithm(algorithm: Algorithm) = copy(algorithm = algorithm)

    fun withChildren(children: List<ComponentBuilder<*>>) = copy(children = children)

    companion object TYPE : ComponentBuilderType<LayoutComponentBuilder>() {
        override fun deserialize(nbt: NbtCompound): LayoutComponentBuilder {
            val algorithm = Algorithm.CODEC.decode(NbtOps.INSTANCE, nbt.get("algorithm")).result().orElseThrow().first

            val children =
                nbt.getList("children", NbtElement.COMPOUND_TYPE.toInt())
                    .map { Companion.deserialize(it as NbtCompound) }

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
                .append(data.algorithm.key)

            //TODO: display children

            out.append("}")

            return out
        }

    }
}