package mod.master_bw3.hexui.fabric

import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.registry.FabricHexUIItems
import net.fabricmc.api.ModInitializer

object FabricHexUI : ModInitializer {
    override fun onInitialize() {
        HexUI.init()

        initRegistries(
            FabricHexUIItems
        )

    }
}