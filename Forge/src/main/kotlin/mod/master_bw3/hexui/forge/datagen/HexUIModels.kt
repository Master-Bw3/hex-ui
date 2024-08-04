@file:OptIn(ExperimentalStdlibApi::class)

package mod.master_bw3.hexui.forge.datagen

import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.items.ItemDebugger
import mod.master_bw3.hexui.items.ItemDebugger.DebugState
import mod.master_bw3.hexui.items.ItemDebugger.StepMode
import mod.master_bw3.hexui.items.ItemEvaluator
import mod.master_bw3.hexui.items.ItemEvaluator.EvalState
import mod.master_bw3.hexui.registry.HexUIItems
import mod.master_bw3.hexui.utils.itemPredicate
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelBuilder
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper

class HexUIModels(output: PackOutput, efh: ExistingFileHelper) : ItemModelProvider(output, HexUI.MODID, efh) {
    override fun registerModels() {
        basicItem(HexUIItems.DUMMY_ITEM.id)
            .parent(ModelFile.UncheckedModelFile("item/handheld_rod"))
    }
}

// utility function for adding multiple possibly missing layers to a generated item model
fun <T : ModelBuilder<T>> T.layers(start: Int, vararg layers: String?): T {
    var index = start
    for (layer in layers) {
        if (layer != null) {
            texture("layer$index", layer)
            index += 1
        }
    }
    return this
}
