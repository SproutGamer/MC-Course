package sproutgamer.mods.mccourse.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import sproutgamer.mods.mccourse.block.ModBlocks
import java.util.concurrent.CompletableFuture

class ModBlockTagProvider(output: FabricDataOutput?,
                          registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {

    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup?) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.FLUORITE_BLOCK,
                ModBlocks.FLUORITE_ORE,
                ModBlocks.DEEPSLATE_FLUORITE_ORE,
                ModBlocks.NETHER_FLUORITE_ORE,
                ModBlocks.END_FLUORITE_ORE)

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.END_FLUORITE_ORE,
                ModBlocks.NETHER_FLUORITE_ORE)

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.DEEPSLATE_FLUORITE_ORE)
    }

}