package mod.master_bw3.hexui.fabric.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class ItemUI(settings: Settings?) : Item(settings) {

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)

        if (world.isClient) {
            screenOpener()
        }

        return TypedActionResult.consume(stack)
    }

    companion object {
        lateinit var screenOpener: (() -> Unit);

    }
}