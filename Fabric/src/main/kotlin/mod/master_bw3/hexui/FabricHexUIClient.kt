package mod.master_bw3.hexui.fabric

import mod.master_bw3.hexui.HexUIClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.api.Environment

@Environment(CLIENT)
object FabricHexUIClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexUIClient.init()
    }
}
