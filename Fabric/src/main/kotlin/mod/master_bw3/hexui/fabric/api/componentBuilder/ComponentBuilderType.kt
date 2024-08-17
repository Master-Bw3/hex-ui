package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import mod.master_bw3.hexui.fabric.registry.ComponentBuilderTypes
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier

abstract class ComponentBuilderType<T: ComponentBuilder<*>> {

    abstract fun deserialize(nbt: NbtCompound): T

    abstract fun color(): Int

    fun typeName(): Text {
        val key = ComponentBuilderTypes.REGISTRY.getKey(this).get().value
        return Text.translatable("hexui.component.$key")
            .styled { style -> style.withColor(TextColor.fromRgb(color())) }
    }

    abstract fun display(nbt: NbtCompound): Text

    companion object {
        fun serialize(componentBuilder: ComponentBuilder<*>): NbtCompound {
            val type = componentBuilder.type
            val typeKey = ComponentBuilderTypes.REGISTRY.getKey(type)
            if (typeKey.isEmpty) {
                throw IllegalStateException("Tried to serialize an unregistered ComponentBuilderType: " + componentBuilder.javaClass.name
                        + " ; Type" + type.javaClass.typeName)
            }

            val dataTag: NbtElement = componentBuilder.serialize()
            val out = NbtCompound()
            out.putString("type", typeKey.get().value.toString())
            out.put("data", dataTag)
            return out;

        }

        fun getTypeFromNbt(nbt: NbtCompound): ComponentBuilderType<*>? {
            if (!nbt.contains("type", NbtElement.STRING_TYPE.toInt())) {
                return null
            }
            val typeKey = nbt.getString("type")
            if (!Identifier.isValid(typeKey)) {
                return null
            }
            val typeLoc: Identifier = Identifier(typeKey)
            return ComponentBuilderTypes.REGISTRY.get(typeLoc)
        }

        fun deserialize(nbt: NbtCompound): ComponentBuilder<*> {
            val type = ComponentBuilderType.getTypeFromNbt(nbt)!!

            val data= nbt.getCompound("data")!!
            val deserialized = type.deserialize(data)

            return deserialized;
        }
    }
}