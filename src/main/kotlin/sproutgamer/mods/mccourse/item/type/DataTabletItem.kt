package sproutgamer.mods.mccourse.item.type

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import sproutgamer.mods.mccourse.component.ModDataComponentTypes

class DataTabletItem(settings: Settings) : Item(settings) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): ActionResult {
        val stack = user!!.getStackInHand(hand)
        if (stack[ModDataComponentTypes.FOUND_BLOCK] != null) {
            stack.remove(ModDataComponentTypes.FOUND_BLOCK)
        }

        return ActionResult.SUCCESS
    }

    override fun hasGlint(stack: ItemStack?): Boolean {
        return stack!![ModDataComponentTypes.FOUND_BLOCK] != null
    }

    override fun appendTooltip(
        stack: ItemStack?,
        context: TooltipContext?,
        tooltip: MutableList<Text>?,
        type: TooltipType?
    ) {
        if (stack!![ModDataComponentTypes.FOUND_BLOCK] != null) {
            tooltip?.add(stack[ModDataComponentTypes.FOUND_BLOCK]?.getOutputText() ?: Text.empty())
        }
    }

}