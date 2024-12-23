package sproutgamer.mods.mccourse.item.type

import net.minecraft.block.Block
import net.minecraft.item.MiningToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class HammerItem(material: ToolMaterial? , attackDamage: Float, attackSpeed: Float,
                 settings: Settings?
) : MiningToolItem(material, BlockTags.PICKAXE_MINEABLE,
    attackDamage,
    attackSpeed, settings
) {

    companion object {

        fun getBlocksToBeDestroyed(range: Int, initialBlockPos: BlockPos, player: ServerPlayerEntity): List<BlockPos> {

            val positions = mutableListOf<BlockPos>()
            val hit = player.raycast(20.0, 0F, false)
            if (hit.type == HitResult.Type.BLOCK) {
                val blockHit = hit as BlockHitResult

                when (blockHit.side!!) {
                    Direction.DOWN -> for (x in -range..range) {
                        for (z in -range..range) {
                            positions.add(BlockPos(initialBlockPos.x + x, initialBlockPos.y, initialBlockPos.z + z))
                        }
                    }
                    Direction.UP -> for (x in -range..range) {
                        for (z in -range..range) {
                            positions.add(BlockPos(initialBlockPos.x + x, initialBlockPos.y, initialBlockPos.z + z))
                        }
                    }
                    Direction.NORTH -> for (x in -range..range) {
                        for (y in -range..range) {
                            positions.add(BlockPos(initialBlockPos.x + x, initialBlockPos.y + y, initialBlockPos.z))
                        }
                    }
                    Direction.SOUTH -> for (x in -range..range) {
                        for (y in -range..range) {
                            positions.add(BlockPos(initialBlockPos.x + x, initialBlockPos.y + y, initialBlockPos.z))
                        }
                    }
                    Direction.WEST -> for (y in -range..range) {
                        for (z in -range..range) {
                            positions.add(BlockPos(initialBlockPos.x, initialBlockPos.y + y, initialBlockPos.z + z))
                        }
                    }
                    Direction.EAST -> for (y in -range..range) {
                        for (z in -range..range) {
                            positions.add(BlockPos(initialBlockPos.x, initialBlockPos.y + y, initialBlockPos.z + z))
                        }
                    }
                }
            }

            return positions

        }

    }

}