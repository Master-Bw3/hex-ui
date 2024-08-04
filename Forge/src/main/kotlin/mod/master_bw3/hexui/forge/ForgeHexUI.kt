package mod.master_bw3.hexui.forge

import dev.architectury.platform.forge.EventBuses
import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.forge.datagen.HexUIModels
import mod.master_bw3.hexui.forge.datagen.HexUIRecipes
import net.minecraft.data.DataProvider
import net.minecraft.data.DataProvider.Factory
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(HexUI.MODID)
class HexUIForge {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(HexUI.MODID, this)
            addListener(ForgeHexUIClient::init)
            addListener(::gatherData)
        }
        HexUI.init()
    }

    private fun gatherData(event: GatherDataEvent) {
        event.apply {
            val efh = existingFileHelper
            addProvider(includeClient()) { HexUIModels(it, efh) }
            addProvider(includeServer()) { HexUIRecipes(it) }
        }
    }
}

fun <T : DataProvider> GatherDataEvent.addProvider(run: Boolean, factory: (PackOutput) -> T) =
    generator.addProvider(run, Factory { factory(it) })
