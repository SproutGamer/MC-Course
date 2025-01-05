package sproutgamer.mods.mccourse.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.item.ItemConvertible
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import sproutgamer.mods.mccourse.item.ModItems

class StrawberriesBlock(settings: Settings?) : CropBlock(settings) {

    companion object {

        const val MAX_AGE = 5
        val AGE: IntProperty = Properties.AGE_5

    }

    override fun getSeedsItem(): ItemConvertible {
        return ModItems.STRAWBERRY_SEEDS
    }

    override fun getAgeProperty(): IntProperty {
        return AGE
    }

    override fun getMaxAge(): Int {
        return MAX_AGE
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(AGE)
    }

}