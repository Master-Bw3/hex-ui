package mod.master_bw3.hexui.fabric.item

import mod.master_bw3.hexui.fabric.screen.HexScreenHandler
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class ItemUI(settings: Settings?) : Item(settings) {

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)

        player.openHandledScreen(object : NamedScreenHandlerFactory {
            override fun createMenu(
                syncId: Int,
                playerInventory: PlayerInventory,
                player: PlayerEntity
            ): ScreenHandler = HexScreenHandler(syncId, playerInventory)


            override fun getDisplayName(): Text = Text.translatable("hexui.screen.hexscreen")

        })

        return TypedActionResult.consume(stack)
    }

    companion object {
        lateinit var screenOpener: (() -> Unit);

    }
}