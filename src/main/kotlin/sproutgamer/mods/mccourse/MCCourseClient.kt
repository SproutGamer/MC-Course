package sproutgamer.mods.mccourse

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.property.ModBooleanProperties

object MCCourseClient : ClientModInitializer {
    override fun onInitializeClient() {
        MCCourse.logger.info("Test")

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_DOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_TRAPDOOR, RenderLayer.getCutout())

        ModBooleanProperties.bootstrap()
    }
}