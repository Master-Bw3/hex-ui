package mod.master_bw3.hexui.fabric.screen

import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import io.wispforest.owo.client.screens.SyncedProperty
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.core.Component
import mod.master_bw3.hexui.fabric.api.componentBuilder.ButtonComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.networking.MsgSetScreenS2C
import mod.master_bw3.hexui.fabric.registry.ScreenHandlers
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import java.util.function.Consumer
import kotlin.reflect.KFunction

class HexScreenHandler(syncId: Int, playerInventory: PlayerInventory) :
    ScreenHandler(ScreenHandlers.HEX_SCREEN.value, syncId) {

    val view: SyncedProperty<ComponentBuilder<*, *>> = createProperty(
        ComponentBuilder::class.java, ButtonComponentBuilder(
            Text.literal("test"),
            IotaType.serialize(ListIota(listOf())),
            listOf()
        )
    )

    override fun quickMove(player: PlayerEntity?, slot: Int) = ItemStack.EMPTY

    override fun canUse(player: PlayerEntity) = true
}