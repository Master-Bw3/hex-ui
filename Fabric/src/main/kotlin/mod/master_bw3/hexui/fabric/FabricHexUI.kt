package mod.master_bw3.hexui.fabric

import gay.`object`.hexdebug.networking.HexUINetworking
import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.networking.MsgSetScreenS2C
import mod.master_bw3.hexui.fabric.registry.ComponentBuilderTypes
import mod.master_bw3.hexui.fabric.registry.FabricHexUIActions
import mod.master_bw3.hexui.fabric.registry.FabricHexUIIotaTypes
import mod.master_bw3.hexui.fabric.registry.FabricHexUIItems
import mod.master_bw3.hexui.initRegistries
import net.fabricmc.api.ModInitializer

object FabricHexUI : ModInitializer {
    override fun onInitialize() {
        HexUI.init()

        initRegistries(
            FabricHexUIItems,
            FabricHexUIActions,
            FabricHexUIIotaTypes,
            ComponentBuilderTypes
        )

        HexUINetworking.CHANNEL.register(MsgSetScreenS2C::class.java, MsgSetScreenS2C::encode, ::MsgSetScreenS2C, MsgSetScreenS2C::apply)
    }
}
