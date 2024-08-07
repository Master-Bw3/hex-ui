package mod.master_bw3.hexui.fabric.registry

import mod.master_bw3.hexui.fabric.screen.HexScreenHandler
import mod.master_bw3.hexui.registry.HexUIRegistrar
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.screen.ScreenHandlerType

object ScreenHandlers :
    HexUIRegistrar<ScreenHandlerType<*>>(RegistryKeys.SCREEN_HANDLER, { Registries.SCREEN_HANDLER }) {

        @JvmField
        var HEX_SCREEN = register("hex_screen"
        ) {
            ScreenHandlerType(
                ::HexScreenHandler,
                FeatureSet.empty()
            )
        }
}