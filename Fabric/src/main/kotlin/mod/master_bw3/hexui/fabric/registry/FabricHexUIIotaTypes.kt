package mod.master_bw3.hexui.fabric.registry

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.item.ItemUI
import mod.master_bw3.hexui.registry.HexUIRegistrar
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys

object FabricHexUIIotaTypes : HexUIRegistrar<IotaType<out Iota>>(HexRegistries.IOTA_TYPE, { HexIotaTypes.REGISTRY }) {

    @JvmField
    val COMPONENT = register("component") { ComponentIota.TYPE }

}