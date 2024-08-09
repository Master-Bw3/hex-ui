package mod.master_bw3.hexui.fabric.screen

import at.petrak.hexcasting.api.casting.SpellList
import at.petrak.hexcasting.api.casting.eval.env.PackagedItemCastEnv
import at.petrak.hexcasting.api.casting.eval.vm.CastingVM
import at.petrak.hexcasting.api.casting.iota.GarbageIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.casting.iota.NullIota
import io.wispforest.owo.client.screens.SyncedProperty
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.api.componentBuilder.ButtonComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.registry.ScreenHandlers
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Hand

class HexScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private var model: Iota,
    private var updateFunction: SpellList,
    private var viewFunction: SpellList
) :
    ScreenHandler(ScreenHandlers.HEX_SCREEN.value, syncId) {

    val view: SyncedProperty<ComponentBuilder<*, *>> = createProperty(
        ComponentBuilder::class.java, ButtonComponentBuilder(
            Text.literal("test"),
            IotaType.serialize(ListIota(listOf())),
            listOf()
        )
    )

    constructor(syncId: Int, playerInventory: PlayerInventory) : this(
        syncId, playerInventory, NullIota(),
        SpellList.LList(listOf()), SpellList.LList(listOf())
    )

    fun update(msg: Iota) {
        val sPlayer: ServerPlayerEntity = player() as ServerPlayerEntity
        val ctx = PackagedItemCastEnv(sPlayer, Hand.MAIN_HAND)
        val harness: CastingVM = CastingVM.empty(ctx)
        harness.image = harness.image.copy(stack = listOf(model, msg))


        val clientView = harness.queueExecuteAndWrapIotas(updateFunction.toList(), sPlayer.serverWorld)

        val result = harness.image.stack

        model = if (result.isNotEmpty()) result.last() else GarbageIota()
    }

    private fun view(model: Iota) {
        val sPlayer = player() as ServerPlayerEntity
        val ctx = PackagedItemCastEnv(sPlayer, Hand.MAIN_HAND)
        val harness: CastingVM = CastingVM.empty(ctx)
        harness.image = harness.image.copy(stack = listOf(model))

        val clientView = harness.queueExecuteAndWrapIotas(viewFunction.toList(), sPlayer.serverWorld)

        val result = harness.image.stack

        this.view.set(result.takeLast(1).getComponent(0, 1))
    }

    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack = ItemStack.EMPTY

    override fun canUse(player: PlayerEntity) = true
}