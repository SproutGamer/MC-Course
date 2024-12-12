package sproutgamer.mods.mccourse.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.item.ModItems

class ModModelProvider(output: FabricDataOutput?) : FabricModelProvider(output) {

    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator?) {
        if (blockStateModelGenerator != null) {
            val fluoriteTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FLUORITE_BLOCK)

            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_ORE)
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_FLUORITE_ORE)
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_FLUORITE_ORE)
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_FLUORITE_ORE)

            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK)

            fluoriteTexturePool.stairs(ModBlocks.FLUORITE_STAIRS)
            fluoriteTexturePool.slab(ModBlocks.FLUORITE_SLAB)
        }
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator?) {
        if (itemModelGenerator != null) {
            itemModelGenerator.register(ModItems.FLUORITE, Models.GENERATED)
            itemModelGenerator.register(ModItems.RAW_FLUORITE, Models.GENERATED)

            itemModelGenerator.register(ModItems.CHAINSAW, Models.GENERATED)
            itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED)
            itemModelGenerator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED)
        }
    }

}