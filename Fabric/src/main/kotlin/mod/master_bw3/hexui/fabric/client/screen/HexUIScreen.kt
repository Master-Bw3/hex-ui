package mod.master_bw3.hexui.fabric.client.screen

import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import mod.master_bw3.hexui.fabric.networking.HexUINetworking
import mod.master_bw3.hexui.fabric.networking.MsgEventCallbackC2S
import mod.master_bw3.hexui.fabric.screen.HexScreenHandler
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import java.util.function.Consumer


class HexUIScreen(
    private var handler: HexScreenHandler,
    inventory: PlayerInventory,
    title: Text
) : BaseOwoScreen<FlowLayout>(title), ScreenHandlerProvider<HexScreenHandler> {

    override fun shouldPause(): Boolean = false

    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    override fun build(rootComponent: FlowLayout) {
        val callback = { nbt: NbtCompound -> HexUINetworking.sendToServer(MsgEventCallbackC2S(nbt)) }

        setView(handler.view.get().build(callback))

        handler.view.observe { builder ->
            setView(builder.build(callback))
        }
    }

    private fun setView(view: Component) {
        this.uiAdapter.rootComponent.clearChildren()
        this.uiAdapter.rootComponent.child(view)

    }

    override fun getScreenHandler(): HexScreenHandler = this.handler

}