package sproutgamer.mods.mccourse.component.type

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import java.util.*

class FoundBlockData(private val block: BlockState, private val position: BlockPos) {

    companion object {
        val CODEC: Codec<FoundBlockData> = RecordCodecBuilder.create { instance ->
            instance.group(BlockState.CODEC.fieldOf("block").forGetter(FoundBlockData::getBlock),
                BlockPos.CODEC.fieldOf("position").forGetter(FoundBlockData::getPosition)).apply(instance, ::FoundBlockData)
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(block, position)
    }

    fun getBlock(): BlockState {
        return block
    }

    fun getPosition(): BlockPos {
        return position
    }

    fun getOutputText(): Text {
        return Text.translatable("component.mccourse.found_block_data.output_text",
            block.block.asItem().name.string,
            position.x,
            position.y,
            position.z)
    }

    override fun equals(other: Any?): Boolean {
        return other is FoundBlockData && block == other.block && position == other.position
    }

}