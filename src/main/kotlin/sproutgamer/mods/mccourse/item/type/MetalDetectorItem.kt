package sproutgamer.mods.mccourse.item.type

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import sproutgamer.mods.mccourse.component.ModDataComponentTypes
import sproutgamer.mods.mccourse.component.type.FoundBlockData
import sproutgamer.mods.mccourse.item.ModItems
import sproutgamer.mods.mccourse.tag.ModTags
import sproutgamer.mods.mccourse.util.InventoryUtil

class MetalDetectorItem(settings: Settings?) : Item(settings) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        if (!context!!.world.isClient) {

            val posClicked = context.blockPos
            val player = context.player!!
            var foundBlock = false

            for (i in 0..posClicked.y) {
                val stateBelow = context.world.getBlockState(posClicked.down(i))
                val blockBelow = stateBelow.block

                if (isValuableBlock(stateBelow)) {
                    outputValuableCoordinates(posClicked.down(i), player, blockBelow)
                    foundBlock = true

                    if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addComponentToStack(player, posClicked.down(i), blockBelow)
                    }

                    context.world.playSound(null, posClicked, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 0.5f, 1f)

                    break
                }
            }

            if (!foundBlock) {
                player.sendMessage(Text.translatable("item.mccourse.metal_detector.no_valuables"), true)
            }

        }

        return ActionResult.SUCCESS
    }

    private fun addComponentToStack(player: PlayerEntity, pos: BlockPos, block: Block) {

        val dataTablet = player.inventory.getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET))

        val data = FoundBlockData(block.defaultState, pos)
        dataTablet[ModDataComponentTypes.FOUND_BLOCK] = data
        dataTablet[ModDataComponentTypes.ON] = true

    }

    private fun outputValuableCoordinates(pos: BlockPos, player: PlayerEntity, block: Block) {
        player.sendMessage(Text.translatable("item.mccourse.metal_detector.valuable_found",
            block.asItem().name.string,
            pos.x,
            pos.y,
            pos.z
        ), true)
    }

    private fun isValuableBlock(block: BlockState): Boolean {
        return block.isIn(ModTags.Blocks.ORES)
    }

}