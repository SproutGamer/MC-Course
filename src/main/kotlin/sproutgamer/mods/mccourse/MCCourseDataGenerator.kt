package sproutgamer.mods.mccourse

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import sproutgamer.mods.mccourse.datagen.tag.ModBlockTagProvider
import sproutgamer.mods.mccourse.datagen.tag.ModItemTagProvider
import sproutgamer.mods.mccourse.datagen.loot.ModBlockLootTableProvider
import sproutgamer.mods.mccourse.datagen.ModModelProvider
import sproutgamer.mods.mccourse.datagen.ModRecipeProvider

object MCCourseDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider(::ModBlockTagProvider)
		pack.addProvider(::ModItemTagProvider)
		pack.addProvider(::ModBlockLootTableProvider)
		pack.addProvider(::ModModelProvider)
		pack.addProvider(::ModRecipeProvider)
	}
}