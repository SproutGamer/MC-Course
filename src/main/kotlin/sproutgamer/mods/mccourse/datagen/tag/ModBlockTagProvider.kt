package sproutgamer.mods.mccourse.datagen.tag

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.tag.ModTags
import java.util.concurrent.CompletableFuture

class ModBlockTagProvider(output: FabricDataOutput?,
                          registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {

    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup?) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.FLUORITE_BLOCK,
                ModBlocks.FLUORITE_ORE,
                ModBlocks.DEEPSLATE_FLUORITE_ORE,
                ModBlocks.NETHER_FLUORITE_ORE,
                ModBlocks.END_FLUORITE_ORE,
                ModBlocks.FLUORITE_STAIRS,
                ModBlocks.FLUORITE_SLAB,
                ModBlocks.FLUORITE_BUTTON,
                ModBlocks.FLUORITE_PRESSURE_PLATE,
                ModBlocks.FLUORITE_FENCE,
                ModBlocks.FLUORITE_FENCE_GATE,
                ModBlocks.FLUORITE_WALL,
                ModBlocks.FLUORITE_DOOR,
                ModBlocks.FLUORITE_TRAPDOOR)

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.END_FLUORITE_ORE,
                ModBlocks.NETHER_FLUORITE_ORE)

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.DEEPSLATE_FLUORITE_ORE)

        getOrCreateTagBuilder(BlockTags.FENCES).add(ModBlocks.FLUORITE_FENCE)
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.FLUORITE_FENCE_GATE)
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.FLUORITE_WALL)

        getOrCreateTagBuilder(ModTags.Blocks.PAXEL_MINEABLE)
            .forceAddTag(BlockTags.PICKAXE_MINEABLE)
            .forceAddTag(BlockTags.AXE_MINEABLE)
            .forceAddTag(BlockTags.SHOVEL_MINEABLE)
    }

}