package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.api.casting.SpellList
import at.petrak.hexcasting.api.casting.iota.Iota
import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound
import java.util.function.Consumer

abstract class ComponentBuilder<T : Widget, V>(
    val type: ComponentBuilderType<*>,
    val properties: V,
    val children: List<ComponentBuilder<*, *>>
) {

    abstract fun serialize(): NbtCompound

    abstract fun build() : T


}