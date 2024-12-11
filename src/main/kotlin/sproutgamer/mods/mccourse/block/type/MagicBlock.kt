package sproutgamer.mods.mccourse.block.type

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import sproutgamer.mods.mccourse.item.ModItems
import sproutgamer.mods.mccourse.util.ModTags

class MagicBlock(settings: Settings?) : Block(settings) {

    override fun onUse(state: BlockState?, world: World?, pos: BlockPos?, player: PlayerEntity?, hit: BlockHitResult?): ActionResult {
        world?.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS)
        return ActionResult.SUCCESS
    }

    override fun onSteppedOn(world: World?, pos: BlockPos?, state: BlockState?, entity: Entity?) {
        if (entity is ItemEntity) {
            if (isValidItem(entity.stack)) {
                entity.stack = ItemStack(Items.DIAMOND, entity.stack.count)
            }
        }

        super.onSteppedOn(world, pos, state, entity)
    }

    private fun isValidItem(stack: ItemStack?): Boolean {
        if (stack != null) {
            return stack.isIn(ModTags.Items.TRANSFORMABLE_ITEMS)
        }
        return false
    }
}