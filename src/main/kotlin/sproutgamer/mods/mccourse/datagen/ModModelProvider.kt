package sproutgamer.mods.mccourse.datagen

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.client.data.BlockStateModelGenerator
import net.minecraft.client.data.ItemModelGenerator
import net.minecraft.client.data.Models
import net.minecraft.item.Items
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
            fluoriteTexturePool.button(ModBlocks.FLUORITE_BUTTON)
            fluoriteTexturePool.pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE)
            fluoriteTexturePool.fence(ModBlocks.FLUORITE_FENCE)
            fluoriteTexturePool.fenceGate(ModBlocks.FLUORITE_FENCE_GATE)
            fluoriteTexturePool.wall(ModBlocks.FLUORITE_WALL)

            blockStateModelGenerator.registerDoor(ModBlocks.FLUORITE_DOOR)
            blockStateModelGenerator.registerTrapdoor(ModBlocks.FLUORITE_TRAPDOOR)

            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUCKY_BLOCK)
        }
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator?) {
        if (itemModelGenerator != null) {
            itemModelGenerator.register(ModItems.FLUORITE, Models.GENERATED)
            itemModelGenerator.register(ModItems.RAW_FLUORITE, Models.GENERATED)

            itemModelGenerator.register(ModItems.CHAINSAW, Models.GENERATED)
            itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED)
            itemModelGenerator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED)

            itemModelGenerator.register(ModItems.FLUORITE_SWORD, Models.HANDHELD)
            itemModelGenerator.register(ModItems.FLUORITE_PICKAXE, Models.HANDHELD)
            itemModelGenerator.register(ModItems.FLUORITE_SHOVEL, Models.HANDHELD)
            itemModelGenerator.register(ModItems.FLUORITE_AXE, Models.HANDHELD)
            itemModelGenerator.register(ModItems.FLUORITE_HOE, Models.HANDHELD)

            itemModelGenerator.registerWithTextureSource(ModItems.EXPLOSIVE_SNOWBALL, Items.SNOWBALL, Models.GENERATED)
        }
    }

}