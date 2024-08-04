package mod.master_bw3.hexui.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import mod.master_bw3.hexui.config.HexUIConfig
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.api.Environment

@Environment(CLIENT)
object FabricHexUIModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexUIConfig::getConfigScreen)
}
