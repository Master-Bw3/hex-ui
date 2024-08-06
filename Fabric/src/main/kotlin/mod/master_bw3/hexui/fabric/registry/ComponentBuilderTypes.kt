package mod.master_bw3.hexui.fabric.registry

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import at.petrak.hexcasting.xplat.IXplatAbstractions
import com.mojang.serialization.Lifecycle
import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.api.componentBuilder.ButtonComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import mod.master_bw3.hexui.fabric.registry.ComponentBuilderTypes.REGISTRY
import mod.master_bw3.hexui.fabric.registry.HexUIRegistryKeys.COMPONENT_BUILDER_TYPE
import mod.master_bw3.hexui.registry.HexUIRegistrar
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.client.gui.widget.Widget
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.SimpleDefaultedRegistry
import net.minecraft.registry.SimpleRegistry

object ComponentBuilderTypes : HexUIRegistrar<ComponentBuilderType<*>>(COMPONENT_BUILDER_TYPE, { REGISTRY }) {
    val REGISTRY: Registry<ComponentBuilderType<*>> =
        FabricRegistryBuilder
            .from(SimpleRegistry(COMPONENT_BUILDER_TYPE, Lifecycle.stable(), false))
            .buildAndRegister()

    @JvmField
    val BUTTON = register("button") { ButtonComponentBuilder.TYPE }

}