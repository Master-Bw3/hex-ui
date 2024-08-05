package mod.master_bw3.hexui.fabric.api.componentBuilder

import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound

abstract class ComponentBuilder<T : Widget, V>(
    val type: ComponentBuilderType<*>,
    val properties: V,
    val children: List<ComponentBuilder<*, *>>
) {

    abstract fun serialize(): NbtCompound
}