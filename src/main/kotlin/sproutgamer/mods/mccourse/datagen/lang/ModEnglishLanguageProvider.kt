package sproutgamer.mods.mccourse.datagen.lang

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.item.equipment.trim.ArmorTrimMaterial
import net.minecraft.item.equipment.trim.ArmorTrimPattern
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.equipment.trim.ModTrimMaterials
import sproutgamer.mods.mccourse.equipment.trim.ModTrimPatterns
import sproutgamer.mods.mccourse.item.ModItems
import java.util.concurrent.CompletableFuture

class ModEnglishLanguageProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(output, registriesFuture) {

    override fun generateTranslations(registries: RegistryWrapper.WrapperLookup?, builder: TranslationBuilder?) {
        builder?.run {
            add(ModItems.FLUORITE, "Fluorite")
            add(ModItems.RAW_FLUORITE, "Raw Fluorite")

            add(ModItems.CHAINSAW, "Chainsaw")
            add(ModItems.CHAINSAW.translationKey + ".tooltip", "This chainsaw will cut down trees fast!")
            add(ModItems.CHAINSAW.translationKey + ".show_tooltip", "§ePress §aSHIFT§e for more info.")
            add(ModItems.CHAINSAW.translationKey + ".coordinates", "Last Chopped Tree at %s")
            add(ModBlocks.MAGIC_BLOCK, "Block of Magic")
            add(ModItems.STRAWBERRY, "Strawberry")
            add(ModItems.STRAWBERRY.translationKey + ".tooltip", "Warning, may contain luck.")

            add(ModItems.FLUORITE_SWORD, "Fluorite Sword")
            add(ModItems.FLUORITE_PICKAXE, "Fluorite Pickaxe")
            add(ModItems.FLUORITE_SHOVEL, "Fluorite Shovel")
            add(ModItems.FLUORITE_AXE, "Fluorite Axe")
            add(ModItems.FLUORITE_HOE, "Fluorite Hoe")
            add(ModItems.FLUORITE_PAXEL, "Fluorite Paxel")
            add(ModItems.FLUORITE_HAMMER, "Fluorite Hammer")

            add(ModItems.FLUORITE_HELMET, "Fluorite Helmet")
            add(ModItems.FLUORITE_CHESTPLATE, "Fluorite Chestplate")
            add(ModItems.FLUORITE_LEGGINGS, "Fluorite Leggings")
            add(ModItems.FLUORITE_BOOTS, "Fluorite Boots")

            add(ModItems.FLUORITE_HORSE_ARMOR, "Fluorite Horse Armor")

            add(ModBlocks.FLUORITE_SLAB, "Fluorite Slab")
            add(ModBlocks.FLUORITE_STAIRS, "Fluorite Stairs")
            add(ModBlocks.FLUORITE_BUTTON, "Fluorite Button")
            add(ModBlocks.FLUORITE_PRESSURE_PLATE, "Fluorite Pressure Plate")
            add(ModBlocks.FLUORITE_FENCE, "Fluorite Fence")
            add(ModBlocks.FLUORITE_FENCE_GATE, "Fluorite Fence Gate")
            add(ModBlocks.FLUORITE_WALL, "Fluorite Wall")
            add(ModBlocks.FLUORITE_DOOR, "Fluorite Door")
            add(ModBlocks.FLUORITE_TRAPDOOR, "Fluorite Trapdoor")

            add(ModItems.STARLIGHT_ASHES, "Starlight Ashes")

            add(ModBlocks.FLUORITE_BLOCK, "Block of Fluorite")
            add(ModBlocks.FLUORITE_ORE, "Fluorite Ore")
            add(ModBlocks.DEEPSLATE_FLUORITE_ORE, "Deepslate Fluorite Ore")
            add(ModBlocks.NETHER_FLUORITE_ORE, "Nether Fluorite Ore")
            add(ModBlocks.END_FLUORITE_ORE, "End Fluorite Ore")

            add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MCCourse.ID, "fluorite")), "Fluorite Group")

            add(ModItems.METAL_DETECTOR, "Metal Detector")
            add(ModItems.METAL_DETECTOR.translationKey + ".no_valuables", "No Valuables Found!")
            add(ModItems.METAL_DETECTOR.translationKey + ".valuable_found", "Found Valuable %s at (%s, %s, %s)")
            add(ModItems.DATA_TABLET, "Data Tablet")

            addTrimMaterial(ModTrimMaterials.FLUORITE, "Fluorite Material", this)
            addTrimPattern(ModTrimPatterns.KAUPEN, "Kaupen Armor Pattern", this)

            add(ModItems.EXPLOSIVE_SNOWBALL, "Explosive Snowball")
            add(ModBlocks.LUCKY_BLOCK, "Lucky Block")
            add(ModBlocks.LUCKY_BLOCK.translationKey + ".event.1.item", "The Stick of Justice")
            add(ModBlocks.LUCKY_BLOCK.translationKey + ".event.4.zombie", "Bob")
            add(ModBlocks.LUCKY_BLOCK.translationKey + ".event.9.dog", "Milo")
            add(ModBlocks.LUCKY_BLOCK.translationKey + ".event.11.item", "Super Furious X-Bow")
            add(ModBlocks.LUCKY_BLOCK.translationKey + ".event.11.item.lore", "§r§fIt shoots... really fast.")

            add("component.mccourse.found_block_data.output_text", "%s at (%s, %s, %s)")
        }
    }

    private fun addTrimMaterial(key: RegistryKey<ArmorTrimMaterial>, value: String, builder: TranslationBuilder) {
        builder.add("trim_material.${key.value.toTranslationKey()}", value)
    }

    private fun addTrimPattern(key: RegistryKey<ArmorTrimPattern>, value: String, builder: TranslationBuilder) {
        builder.add("trim_pattern.${key.value.toTranslationKey()}", value)
    }

}