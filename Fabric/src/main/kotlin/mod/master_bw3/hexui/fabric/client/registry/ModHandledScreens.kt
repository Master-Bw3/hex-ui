package mod.master_bw3.hexui.fabric.client.registry

import mod.master_bw3.hexui.fabric.client.screen.HexUIScreen
import mod.master_bw3.hexui.fabric.registry.ScreenHandlers
import net.minecraft.client.gui.screen.ingame.HandledScreens

object ModHandledScreens {
    fun register() {
        HandledScreens.register(ScreenHandlers.HEX_SCREEN.value, ::HexUIScreen)
    }
}