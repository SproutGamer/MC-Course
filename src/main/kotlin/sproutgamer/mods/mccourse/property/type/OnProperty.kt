package sproutgamer.mods.mccourse.property.type

import com.mojang.serialization.MapCodec
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.item.property.bool.BooleanProperty
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ModelTransformationMode
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.component.ModDataComponentTypes

@Environment(EnvType.CLIENT)
class OnProperty : BooleanProperty {
    override fun getValue(
        stack: ItemStack,
        world: ClientWorld?,
        user: LivingEntity?,
        seed: Int,
        modelTransformationMode: ModelTransformationMode
    ): Boolean {
        MCCourse.logger.info("EEEEEEE")
        return stack[ModDataComponentTypes.ON] ?: false
    }

    override fun getCodec(): MapCodec<OnProperty> {
        MCCourse.logger.info("EEEEEE")
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<OnProperty> = MapCodec.unit(OnProperty())
    }

    init {
        MCCourse.logger.info("EEEEE")
    }
}