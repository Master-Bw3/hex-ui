package mod.master_bw3.hexui.fabric.registry

import mod.master_bw3.hexui.fabric.item.ItemUI
import mod.master_bw3.hexui.registry.HexUIRegistrar
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys

object FabricHexUIItems : HexUIRegistrar<Item>(RegistryKeys.ITEM, { Registries.ITEM }) {

    @JvmField
    val UI_OPENER = register("test") { ItemUI(Settings()) }

}