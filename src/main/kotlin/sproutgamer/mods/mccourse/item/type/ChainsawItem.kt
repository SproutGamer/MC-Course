package sproutgamer.mods.mccourse.item.type

import net.minecraft.client.gui.screen.Screen
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import sproutgamer.mods.mccourse.component.ModDataComponentTypes

class ChainsawItem(settings: Settings?) : Item(settings) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        val world = context?.world

        if (!world?.isClient()!!) {
            if (world.getBlockState(context.blockPos).isIn(BlockTags.LOGS)) {
                world.breakBlock(context.blockPos, true, context.player)

                context.stack.damage(1, world as ServerWorld, context.player as ServerPlayerEntity) { item ->
                    (context.player as ServerPlayerEntity).sendEquipmentBreakStatus(
                        item,
                        EquipmentSlot.MAINHAND
                    )
                }

                context.stack[ModDataComponentTypes.COORDINATES] = context.blockPos
            }
        }

        return ActionResult.CONSUME
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): ActionResult {
        val stack = user?.getEquippedStack(EquipmentSlot.MAINHAND)

        if (stack!![ModDataComponentTypes.COORDINATES] != null) {
            stack.remove(ModDataComponentTypes.COORDINATES)
        }

        return ActionResult.SUCCESS
    }

    override fun appendTooltip(
        stack: ItemStack?,
        context: TooltipContext?,
        tooltip: MutableList<Text>?,
        type: TooltipType?
    ) {
        tooltip?.add(
            if (!Screen.hasShiftDown()) {
                Text.translatable("item.mccourse.chainsaw.show_tooltip")
            } else {
                Text.translatable("item.mccourse.chainsaw.tooltip")
            }
        )

        if (stack!![ModDataComponentTypes.COORDINATES] != null) {
            tooltip?.add(Text.translatable("item.mccourse.chainsaw.coordinates", stack[ModDataComponentTypes.COORDINATES]))
        }

        super.appendTooltip(stack, context, tooltip, type)
    }

}
