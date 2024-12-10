package sproutgamer.mods.mccourse.item.type

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult

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

            }
        }

        return ActionResult.CONSUME
    }

}
