package mod.master_bw3.hexui

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.api.Environment

@Environment(CLIENT)
object HexUIClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexUIClient.init()
    }

    public fun init() {

    }
}
