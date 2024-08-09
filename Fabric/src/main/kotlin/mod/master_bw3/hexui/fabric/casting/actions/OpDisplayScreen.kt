package mod.master_bw3.hexui.fabric.casting.actions

import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.OperationResult
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughArgs
import at.petrak.hexcasting.common.lib.hex.HexEvalSounds
import gay.`object`.hexdebug.networking.HexUINetworking
import mod.master_bw3.hexui.fabric.api.casting.continuation.FrameComputeScreenView
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.api.casting.iota.ComponentIota
import mod.master_bw3.hexui.fabric.networking.MsgSetScreenS2C
import mod.master_bw3.hexui.fabric.screen.HexScreenHandler
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

object OpDisplayScreen : Action {
    val argc: Int
        get() = 3

    override fun operate(
        env: CastingEnvironment,
        image: CastingImage,
        continuation: SpellContinuation
    ): OperationResult {
        val stack = image.stack.toMutableList()

        if (this.argc > stack.size)
            throw MishapNotEnoughArgs(this.argc, stack.size)
        val args = stack.takeLast(this.argc)
        repeat(this.argc) { stack.removeLast() }

        val init = args[0]
        val update = args.getList(1, argc)
        val view = args.getList(2, argc)

        val caster = env.castingEntity
        if (caster is ServerPlayerEntity) {
            caster.openHandledScreen(object : NamedScreenHandlerFactory {
                override fun createMenu(
                    syncId: Int,
                    playerInventory: PlayerInventory,
                    player: PlayerEntity
                ): ScreenHandler = HexScreenHandler(syncId, playerInventory, init, update, view)


                override fun getDisplayName(): Text = Text.translatable("hexui.screen.hexscreen")
            })


        }

        return OperationResult(
            image.copy(stack = stack, opsConsumed = 1),
            listOf(),
            continuation.pushFrame(FrameComputeScreenView(stack, init, view)),
            HexEvalSounds.NORMAL_EXECUTE
        )
    }
}