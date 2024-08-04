package mod.master_bw3.hexui

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

object HexUI : ModInitializer {

    const val MODID = "hexui"

    fun id(path: String) = Identifier(MODID, path)

    override fun onInitialize() {
        HexUI.init()
    }

    fun init() {

    }

}
