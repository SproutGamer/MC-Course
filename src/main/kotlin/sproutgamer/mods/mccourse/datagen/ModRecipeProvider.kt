package sproutgamer.mods.mccourse.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.item.ModItems
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: FabricDataOutput?,
                        registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?) : FabricRecipeProvider(output, registriesFuture) {

    override fun generate(exporter: RecipeExporter?) {
        val fluoriteSmeltables = listOf(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE,
            ModBlocks.DEEPSLATE_FLUORITE_ORE, ModBlocks.END_FLUORITE_ORE, ModBlocks.NETHER_FLUORITE_ORE)

        offerSmelting(exporter, fluoriteSmeltables, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite")

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.FLUORITE, RecipeCategory.DECORATIONS, ModBlocks.FLUORITE_BLOCK)

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_SLAB, ModItems.FLUORITE)
        offerStairsRecipe(exporter, ModBlocks.FLUORITE_STAIRS, ModItems.FLUORITE)

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
            .pattern("SSS")
            .pattern("SFS")
            .pattern("SSS")
            .input('S', Blocks.STONE)
            .input('F', ModItems.FLUORITE)
            .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
            .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
            .offerTo(exporter)
    }

    private fun offerStairsRecipe(exporter: RecipeExporter?, output: ItemConvertible?, input: ItemConvertible?) {
        createStairsRecipe(output, Ingredient.ofItems(input))
            .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
            .offerTo(exporter)
    }

}