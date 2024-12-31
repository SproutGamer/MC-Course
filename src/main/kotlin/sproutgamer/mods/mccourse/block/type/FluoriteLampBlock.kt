package sproutgamer.mods.mccourse.block.type

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World

class FluoriteLampBlock(settings: Settings?) : Block(settings) {
    companion object {

        val LUMINANCE: IntProperty = IntProperty.of("luminance", 0, 15)
        val LIT: BooleanProperty = BooleanProperty.of("lit")

    }

    init {
        defaultState = stateManager.defaultState
            .with(LIT, false)
            .with(LUMINANCE, 0)
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hit: BlockHitResult?
    ): ActionResult {
        if (!world!!.isClient) {
            updateLampState(state, world, pos)
        }

        return ActionResult.SUCCESS
    }

    private fun updateLampState(
        state: BlockState?,
        world: World,
        pos: BlockPos?
    ) {
        var newState = state!!.cycle(LUMINANCE)
        newState = newState.with(LIT, newState[LUMINANCE] > 0)
        world.setBlockState(pos, newState)
    }



    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(LUMINANCE)
        builder?.add(LIT)
    }
}