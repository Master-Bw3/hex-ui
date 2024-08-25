package mod.master_bw3.hexui.fabric.componentBuilder.container

import at.petrak.hexcasting.api.casting.iota.IotaType
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.GridLayout
import mod.master_bw3.hexui.fabric.api.casting.SizingData
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import mod.master_bw3.hexui.fabric.api.componentBuilder.HasChildren
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtOps
import net.minecraft.text.Text
import net.minecraft.util.dynamic.Codecs

data class GridLayoutComponentBuilder(
    val horizontalSizing: SizingData, val verticalSizing: SizingData, val rows: Int, val columns: Int,
    override val children: List<ComponentBuilder<*>> = listOf(),
) : ComponentBuilder<GridLayout>(TYPE), HasChildren<GridLayoutComponentBuilder> {

    override fun serialize(): NbtCompound {
        val nbt = NbtCompound();
        nbt.put("horizontalSizing", SizingData.CODEC.encodeStart(NbtOps.INSTANCE, horizontalSizing).result().orElseThrow())
        nbt.put("verticalSizing", SizingData.CODEC.encodeStart(NbtOps.INSTANCE, verticalSizing).result().orElseThrow())
        nbt.put("rows", Codecs.POSITIVE_INT.encodeStart(NbtOps.INSTANCE, rows).result().orElseThrow())
        nbt.put("columns", Codecs.POSITIVE_INT.encodeStart(NbtOps.INSTANCE, columns).result().orElseThrow())

        val childrenNbt = NbtList()
        childrenNbt.addAll(children.map { ComponentBuilderType.serialize(it) })

        nbt.put("children", childrenNbt)

        return nbt;
    }

    override fun build(eventCallbackHandler: (NbtCompound) -> Unit): GridLayout {
        return Containers.grid(
            horizontalSizing.toSizing(),
            verticalSizing.toSizing(),
            rows,
            columns
        )
    }

    companion object TYPE : ComponentBuilderType<GridLayoutComponentBuilder>() {
        override fun deserialize(nbt: NbtCompound): GridLayoutComponentBuilder {
            val horizontalSizing = SizingData.CODEC.parse(NbtOps.INSTANCE, nbt.getCompound("horizontalSizing")).result().orElseThrow()
            val verticalSizing = SizingData.CODEC.parse(NbtOps.INSTANCE, nbt.getCompound("verticalSizing")).result().orElseThrow()
            val rows = Codecs.POSITIVE_INT.parse(NbtOps.INSTANCE, nbt.get("rows")).result().orElseThrow()
            val columns = Codecs.POSITIVE_INT.parse(NbtOps.INSTANCE, nbt.get("columns")).result().orElseThrow()

            val children =
                nbt.getList("children", NbtElement.COMPOUND_TYPE.toInt())
                    .map { Companion.deserialize(it as NbtCompound) }

            return GridLayoutComponentBuilder(horizontalSizing, verticalSizing, rows, columns, children)
        }

        override fun color(): Int {
            return 0xFFFFFF
        }

        override fun display(nbt: NbtCompound): Text {
            val propertiesKey = "hexui.component.button.properties"

            val out = typeName().copy()
                .append("{ ")

            val data = deserialize(nbt.getCompound("data"))

            out.append(Text.translatable("${propertiesKey}.verticalSizing"))
                .append(": \"")
                .append(data.verticalSizing.toString())
                .append("\", ")

            out.append(Text.translatable("${propertiesKey}.horizontalSizing"))
                .append(": \"")
                .append(data.horizontalSizing.toString())
                .append("\", ")

            out.append(Text.translatable("${propertiesKey}.rows"))
                .append(": \"")
                .append(data.rows.toString())
                .append("\", ")

            out.append(Text.translatable("${propertiesKey}.columns"))
                .append(": \"")
                .append(data.columns.toString())
                .append("\", ")

            out.append("}")

            return out
        }

    }

    override fun withChildren(children: List<ComponentBuilder<*>>) = copy(children = children)

    override fun addChild(child: ComponentBuilder<*>) = copy(children = children.plus(child))
}