package sproutgamer.mods.mccourse.component

import com.mojang.serialization.Codec
import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.component.type.FoundBlockData

object ModDataComponentTypes {

    val COORDINATES = register("coordinates") { builder -> builder.codec(BlockPos.CODEC) }
    val FOUND_BLOCK = register("found_block") { builder -> builder.codec(FoundBlockData.CODEC) }

    val ON = register("on") { builder -> builder.codec(Codec.BOOL) }

    private fun <T> register(
        path: String,
        builderOperator: (ComponentType.Builder<T>) -> ComponentType.Builder<T>
    ): ComponentType<T> {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE, Identifier.of(MCCourse.ID, path),
            builderOperator(ComponentType.builder()).build()
        )
    }

    fun initialize(logger: Logger) {
        logger.info("Initializing Data Component Types…")
    }

}