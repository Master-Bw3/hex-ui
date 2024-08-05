package mod.master_bw3.hexui.fabric.registry

import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import net.minecraft.client.gui.widget.Widget
import net.minecraft.registry.RegistryKey

object HexUIRegistryKeys {
    val COMPONENT_BUILDER_TYPE = RegistryKey.ofRegistry<ComponentBuilderType<*>>(HexUI.id("component_builder_type"))

}