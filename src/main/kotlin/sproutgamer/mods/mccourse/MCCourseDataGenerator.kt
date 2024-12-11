package sproutgamer.mods.mccourse

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import sproutgamer.mods.mccourse.datagen.ModBlockTagProvider
import sproutgamer.mods.mccourse.datagen.ModItemTagProvider
import sproutgamer.mods.mccourse.datagen.ModLootTableProvider
import sproutgamer.mods.mccourse.datagen.ModModelProvider
import sproutgamer.mods.mccourse.datagen.ModRecipeProvider

object MCCourseDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider(::ModBlockTagProvider)
		pack.addProvider(::ModItemTagProvider)
		pack.addProvider(::ModLootTableProvider)
		pack.addProvider(::ModModelProvider)
		pack.addProvider(::ModRecipeProvider)
	}
}