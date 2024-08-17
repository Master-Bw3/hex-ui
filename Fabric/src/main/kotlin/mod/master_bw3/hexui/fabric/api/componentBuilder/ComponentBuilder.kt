package mod.master_bw3.hexui.fabric.api.componentBuilder

import at.petrak.hexcasting.api.casting.SpellList
import at.petrak.hexcasting.api.casting.iota.Iota
import io.wispforest.owo.ui.core.Component
import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound
import java.util.function.Consumer
import kotlin.reflect.full.createInstance

abstract class ComponentBuilder<T : Component>(
    val type: ComponentBuilderType<*>,
) : Cloneable {

    abstract fun serialize(): NbtCompound

    abstract fun build(eventCallbackHandler: (NbtCompound) -> Unit) : T
}