package mod.master_bw3.hexui.fabric.registry

import com.mojang.serialization.Lifecycle
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import mod.master_bw3.hexui.fabric.componentBuilder.component.ButtonComponentBuilder
import mod.master_bw3.hexui.fabric.componentBuilder.container.LayoutComponentBuilder
import mod.master_bw3.hexui.fabric.registry.ComponentBuilderTypes.REGISTRY
import mod.master_bw3.hexui.fabric.registry.HexUIRegistryKeys.COMPONENT_BUILDER_TYPE
import mod.master_bw3.hexui.registry.HexUIRegistrar
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.registry.Registry
import net.minecraft.registry.SimpleRegistry

object ComponentBuilderTypes : HexUIRegistrar<ComponentBuilderType<*>>(COMPONENT_BUILDER_TYPE, { REGISTRY }) {
    val REGISTRY: Registry<ComponentBuilderType<*>> =
        FabricRegistryBuilder
            .from(SimpleRegistry(COMPONENT_BUILDER_TYPE, Lifecycle.stable(), false))
            .buildAndRegister()

    @JvmField
    val BUTTON = register("button") { ButtonComponentBuilder.TYPE }

    @JvmField
    val LAYOUT = register("layout") { LayoutComponentBuilder.TYPE }
}