package mod.master_bw3.hexui

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object HexUI : ModInitializer {

    const val MODID = "hexui"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    fun id(path: String) = Identifier(MODID, path)

    override fun onInitialize() {
        HexUI.init()
    }

    fun init() {
    }

}
