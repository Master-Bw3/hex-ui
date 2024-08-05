package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.api.HexAPI
import at.petrak.hexcasting.api.casting.iota.GarbageIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.NullIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import mod.master_bw3.hexui.fabric.registry.ComponentBuilderTypes
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import java.util.*

abstract class ComponentBuilderType<T: ComponentBuilder<*, *>> {

    abstract fun deserialize(nbt: NbtCompound, world: ServerWorld): T

    companion object {
        fun serialize(componentBuilder: ComponentBuilder<*, *>): NbtCompound {
            val type = componentBuilder.type
            val typeID = ComponentBuilderTypes.REGISTRY.getKey(type)
            checkNotNull(typeID) {
                ("Tried to serialize an unregistered ComponentBuilderType: " + componentBuilder.javaClass.name
                        + " ; Type" + type.javaClass.typeName)
            }

            val dataTag: NbtElement = componentBuilder.serialize()
            val out: NbtCompound = NbtCompound()
            out.putString("type", typeID.toString())
            out.put("data", dataTag)
            return out;

        }

        fun getTypeFromNbt(nbt: NbtCompound): ComponentBuilderType<*>? {
            if (!nbt.contains(HexIotaTypes.KEY_TYPE, NbtElement.STRING_TYPE.toInt())) {
                return null
            }
            val typeKey = nbt.getString("type")
            if (!Identifier.isValid(typeKey)) {
                return null
            }
            val typeLoc: Identifier = Identifier(typeKey)
            return ComponentBuilderTypes.REGISTRY.get(typeLoc)
        }

        fun deserialize(nbt: NbtCompound, world: ServerWorld): ComponentBuilder<*, *> {
            val type = ComponentBuilderType.getTypeFromNbt(nbt)!!

            val data= nbt.getCompound("data")!!
            val deserialized = type.deserialize(data, world)

            return deserialized;
        }
    }
}