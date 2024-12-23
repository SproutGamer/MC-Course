package sproutgamer.mods.mccourse.event

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import sproutgamer.mods.mccourse.item.type.HammerItem

class HammerUsageEvent : PlayerBlockBreakEvents.Before {

    companion object {

        val HARVESTED_BLOCKS = mutableSetOf<BlockPos>()

    }

    override fun beforeBlockBreak(
        world: World?,
        player: PlayerEntity?,
        pos: BlockPos?,
        state: BlockState?,
        blockEntity: BlockEntity?
    ): Boolean {

        val mainHandItem = player!!.mainHandStack

        if (mainHandItem.item is HammerItem && player is ServerPlayerEntity) {
            val hammer = mainHandItem.item

            if (HARVESTED_BLOCKS.contains(pos!!)) {
                return true
            }

            for (position in HammerItem.getBlocksToBeDestroyed(3, pos, player)) {
                if (pos == position || !hammer.isCorrectForDrops(mainHandItem, world!!.getBlockState(position))) {
                    continue
                }

                HARVESTED_BLOCKS.add(position)
                player.interactionManager.tryBreakBlock(position)
                HARVESTED_BLOCKS.remove(position)
            }
        }

        return true

    }

}