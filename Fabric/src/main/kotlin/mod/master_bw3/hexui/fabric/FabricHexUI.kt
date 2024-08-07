package mod.master_bw3.hexui.fabric

import gay.`object`.hexdebug.networking.HexUINetworking
import io.wispforest.owo.network.serialization.PacketBufSerializer
import mod.master_bw3.hexui.HexUI
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import mod.master_bw3.hexui.fabric.networking.MsgSetScreenS2C
import mod.master_bw3.hexui.fabric.registry.*
import mod.master_bw3.hexui.initRegistries
import net.fabricmc.api.ModInitializer

object FabricHexUI : ModInitializer {
    override fun onInitialize() {
        HexUI.init()

        initRegistries(
            FabricHexUIItems,
            FabricHexUIActions,
            FabricHexUIIotaTypes,
            ComponentBuilderTypes,
            ScreenHandlers
        )

        PacketBufSerializer.register(
            ComponentBuilder::class.java, PacketBufSerializer(
                { buf, builder -> buf.writeNbt(ComponentBuilderType.serialize(builder)) },
                { buf -> ComponentBuilderType.deserialize(buf.readNbt()!!) }
            )
        )
    }
}
