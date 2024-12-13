package sproutgamer.mods.mccourse.block.type

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import sproutgamer.mods.mccourse.event.LuckyBlockEvents

class LuckyBlock(settings: Settings?) : Block(settings) {

    override fun onBreak(world: World?, pos: BlockPos?, state: BlockState?, player: PlayerEntity?): BlockState {
        if (!player!!.isCreative) {
            val events = LuckyBlockEvents(world!!, player, pos!!)
            events.triggerRandomEvent()
        }

        return super.onBreak(world, pos, state, player)
    }

}
