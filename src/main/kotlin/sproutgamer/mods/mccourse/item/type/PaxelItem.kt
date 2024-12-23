package sproutgamer.mods.mccourse.item.type

import com.google.common.collect.BiMap
import net.minecraft.advancement.criterion.Criteria
import net.minecraft.block.*
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.WorldEvents
import net.minecraft.world.event.GameEvent
import sproutgamer.mods.mccourse.tag.ModTags
import java.util.*

class PaxelItem(
    material: ToolMaterial?, attackDamage: Float, attackSpeed: Float, settings: Settings?
) : MiningToolItem(material, ModTags.Blocks.PAXEL_MINEABLE,
    attackDamage,
    attackSpeed, settings
) {

    companion object {

        private val PATH_STATES = mapOf(
            Blocks.GRASS_BLOCK to Blocks.DIRT_PATH.defaultState,
            Blocks.DIRT to Blocks.DIRT_PATH.defaultState,
            Blocks.PODZOL to Blocks.DIRT_PATH.defaultState,
            Blocks.COARSE_DIRT to Blocks.DIRT_PATH.defaultState,
            Blocks.MYCELIUM to Blocks.DIRT_PATH.defaultState,
            Blocks.ROOTED_DIRT to Blocks.DIRT_PATH.defaultState
        )

        private val STRIPPED_BLOCKS = mapOf(
            Blocks.OAK_WOOD to Blocks.STRIPPED_OAK_WOOD,
            Blocks.OAK_LOG to Blocks.STRIPPED_OAK_LOG,
            Blocks.DARK_OAK_WOOD to Blocks.STRIPPED_DARK_OAK_WOOD,
            Blocks.DARK_OAK_LOG to Blocks.STRIPPED_DARK_OAK_LOG,
            Blocks.PALE_OAK_WOOD to Blocks.STRIPPED_PALE_OAK_WOOD,
            Blocks.PALE_OAK_LOG to Blocks.STRIPPED_PALE_OAK_LOG,
            Blocks.ACACIA_WOOD to Blocks.STRIPPED_ACACIA_WOOD,
            Blocks.ACACIA_LOG to Blocks.STRIPPED_ACACIA_LOG,
            Blocks.CHERRY_WOOD to Blocks.STRIPPED_CHERRY_WOOD,
            Blocks.CHERRY_LOG to Blocks.STRIPPED_CHERRY_LOG,
            Blocks.BIRCH_WOOD to Blocks.STRIPPED_BIRCH_WOOD,
            Blocks.BIRCH_LOG to Blocks.STRIPPED_BIRCH_LOG,
            Blocks.JUNGLE_WOOD to Blocks.STRIPPED_JUNGLE_WOOD,
            Blocks.JUNGLE_LOG to Blocks.STRIPPED_JUNGLE_LOG,
            Blocks.SPRUCE_WOOD to Blocks.STRIPPED_SPRUCE_WOOD,
            Blocks.SPRUCE_LOG to Blocks.STRIPPED_SPRUCE_LOG,
            Blocks.WARPED_STEM to Blocks.STRIPPED_WARPED_STEM,
            Blocks.WARPED_HYPHAE to Blocks.STRIPPED_WARPED_HYPHAE,
            Blocks.CRIMSON_STEM to Blocks.STRIPPED_CRIMSON_STEM,
            Blocks.CRIMSON_HYPHAE to Blocks.STRIPPED_CRIMSON_HYPHAE,
            Blocks.MANGROVE_WOOD to Blocks.STRIPPED_MANGROVE_WOOD,
            Blocks.MANGROVE_LOG to Blocks.STRIPPED_MANGROVE_LOG,
            Blocks.BAMBOO_BLOCK to Blocks.STRIPPED_BAMBOO_BLOCK
        )

    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val blockPos = context.blockPos
        val blockState = world.getBlockState(blockPos)

        val playerEntity = context.player
        if (shouldCancelStripAttempt(context)) {
            return ActionResult.PASS
        } else {
            val optional = this.tryStrip(world, blockPos, playerEntity, world.getBlockState(blockPos))
            if (optional.isEmpty) {
                return ActionResult.PASS
            } else {
                val itemStack = context.stack
                if (playerEntity is ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger(playerEntity, blockPos, itemStack)
                }

                world.setBlockState(blockPos, optional.get(), Block.NOTIFY_ALL_AND_REDRAW)
                world.emitGameEvent(
                    GameEvent.BLOCK_CHANGE,
                    blockPos,
                    GameEvent.Emitter.of(playerEntity, optional.get())
                )
                if (playerEntity != null) {
                    itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.hand))
                }

            }
        }

        if (context.side == Direction.DOWN) {
            return ActionResult.PASS
        } else {
            val blockState2 = PATH_STATES[blockState.block]
            var blockState3: BlockState? = null
            if (blockState2 != null && world.getBlockState(blockPos.up()).isAir) {
                world.playSound(
                    playerEntity,
                    blockPos,
                    SoundEvents.ITEM_SHOVEL_FLATTEN,
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
                )
                blockState3 = blockState2
            } else if (blockState.block is CampfireBlock && blockState.get(CampfireBlock.LIT) as Boolean) {
                if (!world.isClient()) {
                    world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, blockPos, 0)
                }

                CampfireBlock.extinguish(context.player, world, blockPos, blockState)
                blockState3 = blockState.with(CampfireBlock.LIT, false)
            }

            if (blockState3 != null) {
                if (!world.isClient) {
                    world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL_AND_REDRAW)
                    world.emitGameEvent(
                        GameEvent.BLOCK_CHANGE,
                        blockPos,
                        GameEvent.Emitter.of(playerEntity, blockState3)
                    )
                    if (playerEntity != null) {
                        context.stack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.hand))
                    }
                }

                return ActionResult.SUCCESS
            } else {
                return ActionResult.PASS
            }
        }
    }

    private fun shouldCancelStripAttempt(context: ItemUsageContext): Boolean {
        val playerEntity = context.player
        return context.hand == Hand.MAIN_HAND && playerEntity!!.offHandStack.isOf(Items.SHIELD) && !playerEntity.shouldCancelInteraction()
    }

    private fun tryStrip(world: World, pos: BlockPos, player: PlayerEntity?, state: BlockState): Optional<BlockState> {
        val optional = this.getStrippedState(state)
        if (optional.isPresent) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f)
            return optional
        } else {
            val optional2 = Oxidizable.getDecreasedOxidationState(state)
            if (optional2.isPresent) {
                world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f)
                world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0)
                return optional2
            } else {
                val optional3 =
                    Optional.ofNullable((HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get() as BiMap<*, *>)[state.block] as Block?)
                        .map { block: Block -> block.getStateWithProperties(state) }
                if (optional3.isPresent) {
                    world.playSound(player, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f)
                    world.syncWorldEvent(player, WorldEvents.WAX_REMOVED, pos, 0)
                    return optional3
                } else {
                    return Optional.empty()
                }
            }
        }
    }

    private fun getStrippedState(state: BlockState): Optional<BlockState> {
        return Optional.ofNullable(STRIPPED_BLOCKS[state.block])
            .map { block: Block ->
                block.defaultState.with(
                    PillarBlock.AXIS,
                    state.get(PillarBlock.AXIS) as Direction.Axis
                )
            }
    }

}