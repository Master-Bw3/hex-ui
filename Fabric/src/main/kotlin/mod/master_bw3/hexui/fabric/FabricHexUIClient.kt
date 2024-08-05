package mod.master_bw3.hexui.fabric

import mod.master_bw3.hexui.HexUIClient
import mod.master_bw3.hexui.fabric.client.MyFirstScreen
import mod.master_bw3.hexui.fabric.item.ItemUI
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient

@Environment(CLIENT)
object FabricHexUIClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexUIClient.init()

        ItemUI.screenOpener = { MinecraftClient.getInstance().setScreen(MyFirstScreen()) }
    }
}
