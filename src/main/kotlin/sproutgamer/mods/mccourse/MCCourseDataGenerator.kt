package sproutgamer.mods.mccourse

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import sproutgamer.mods.mccourse.datagen.ModModelProvider
import sproutgamer.mods.mccourse.datagen.ModRecipeProvider
import sproutgamer.mods.mccourse.datagen.ModRegistryDataProvider
import sproutgamer.mods.mccourse.datagen.loot.ModBlockLootTableProvider
import sproutgamer.mods.mccourse.datagen.tag.ModBlockTagProvider
import sproutgamer.mods.mccourse.datagen.tag.ModItemTagProvider
import sproutgamer.mods.mccourse.equipment.trim.ModTrimMaterials
import sproutgamer.mods.mccourse.equipment.trim.ModTrimPatterns

object MCCourseDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider(::ModBlockTagProvider)
		pack.addProvider(::ModItemTagProvider)
		pack.addProvider(::ModBlockLootTableProvider)
		pack.addProvider(::ModModelProvider)
		pack.addProvider(::ModRecipeProvider)
		pack.addProvider(::ModRegistryDataProvider)
	}

	override fun buildRegistry(registryBuilder: RegistryBuilder?) {
		registryBuilder?.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
		registryBuilder?.addRegistry(RegistryKeys.TRIM_PATTERN, ModTrimPatterns::bootstrap)
	}
}