package mod.master_bw3.hexui.fabric.api.casting.iota

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text

class ComponentIota<T : ComponentBuilder<*>>(payload: T) : Iota(TYPE, payload) {

    override fun isTruthy(): Boolean = true

    override fun toleratesOther(that: Iota?): Boolean =
        that is ComponentIota<*> && that.component == component


    override fun serialize(): NbtElement = ComponentBuilderType.serialize(component)

    val component: ComponentBuilder<*>
        get() = ((this).payload as ComponentBuilder<*>)

    companion object TYPE : IotaType<ComponentIota<*>>() {
        override fun deserialize(nbt: NbtElement, world: ServerWorld): ComponentIota<*> =
            ComponentIota(ComponentBuilderType.deserialize(nbt as NbtCompound))


        override fun display(tag: NbtElement): Text =
            ComponentBuilderType.getTypeFromNbt(tag as NbtCompound)!!.display(tag)


        override fun color(): Int = 0xFFFFFF

    }
}