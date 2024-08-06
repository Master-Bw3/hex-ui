package mod.master_bw3.hexui.fabric

import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.registry.ComponentBuilderTypes
import mod.master_bw3.hexui.fabric.registry.FabricHexUIActions
import mod.master_bw3.hexui.fabric.registry.FabricHexUIIotaTypes
import mod.master_bw3.hexui.fabric.registry.FabricHexUIItems
import mod.master_bw3.hexui.initRegistries
import net.fabricmc.api.ModInitializer

object FabricHexUI : ModInitializer {
    override fun onInitialize() {
        HexUI.init()

        initRegistries(
            FabricHexUIItems,
            FabricHexUIActions,
            FabricHexUIIotaTypes,
            ComponentBuilderTypes
        )
    }
}
