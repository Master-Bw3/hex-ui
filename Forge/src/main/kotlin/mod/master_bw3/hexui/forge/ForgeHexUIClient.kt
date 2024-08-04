package mod.master_bw3.hexui.forge

import mod.master_bw3.hexui.HexUIClient
import mod.master_bw3.hexui.config.HexUIConfig
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT

object ForgeHexUIClient {
    fun init(event: FMLClientSetupEvent) {
        HexUIClient.init()
        LOADING_CONTEXT.registerExtensionPoint(ConfigScreenFactory::class.java) {
            ConfigScreenFactory { _, parent -> HexUIConfig.getConfigScreen(parent) }
        }
    }
}
