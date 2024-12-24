package sproutgamer.mods.mccourse.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.recipe.RecipeExporter
import net.minecraft.data.recipe.RecipeGenerator
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.item.ModItems
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: FabricDataOutput?,
                        registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?) : FabricRecipeProvider(output, registriesFuture) {

    override fun getName(): String {
        return "MC Course Recipe Provider"
    }

    override fun getRecipeGenerator(wrapperLookup: WrapperLookup, exporter: RecipeExporter?): RecipeGenerator {
        return object : RecipeGenerator(wrapperLookup, exporter) {
            override fun generate() {
                val fluoriteSmeltables = listOf(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE,
                    ModBlocks.DEEPSLATE_FLUORITE_ORE, ModBlocks.END_FLUORITE_ORE, ModBlocks.NETHER_FLUORITE_ORE)

                offerSmelting(fluoriteSmeltables, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite")
                offerBlasting(fluoriteSmeltables, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite")

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.FLUORITE, RecipeCategory.DECORATIONS, ModBlocks.FLUORITE_BLOCK)

                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_SLAB, ModItems.FLUORITE)
                offerStairsRecipe(ModBlocks.FLUORITE_STAIRS, ModItems.FLUORITE)

                createButtonRecipe(ModBlocks.FLUORITE_BUTTON, Ingredient.ofItems(ModItems.FLUORITE))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)
                offerPressurePlateRecipe(ModBlocks.FLUORITE_PRESSURE_PLATE, ModItems.FLUORITE)

                createFenceRecipe(ModBlocks.FLUORITE_FENCE, Ingredient.ofItems(ModItems.FLUORITE))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)
                createFenceGateRecipe(ModBlocks.FLUORITE_FENCE_GATE, Ingredient.ofItems(ModItems.FLUORITE))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_WALL, ModItems.FLUORITE)

                createDoorRecipe(ModBlocks.FLUORITE_DOOR, Ingredient.ofItems(ModItems.FLUORITE))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)
                createTrapdoorRecipe(ModBlocks.FLUORITE_TRAPDOOR, Ingredient.ofItems(ModItems.FLUORITE))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)

                createShaped(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
                    .pattern("SSS")
                    .pattern("SFS")
                    .pattern("SSS")
                    .input('S', Blocks.STONE)
                    .input('F', ModItems.FLUORITE)
                    .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)

                offerSmithingTrimRecipe(ModItems.KAUPEN_SMITHING_TEMPLATE, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MCCourse.MOD_ID, "kaupen")))
            }

            private fun offerStairsRecipe(output: ItemConvertible?, input: ItemConvertible?) {
                createStairsRecipe(output, Ingredient.ofItems(input))
                    .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                    .offerTo(exporter)
            }
        }
    }
}