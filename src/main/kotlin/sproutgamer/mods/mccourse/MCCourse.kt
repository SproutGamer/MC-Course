package sproutgamer.mods.mccourse

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.ItemGroups
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.item.ModItemGroups
import sproutgamer.mods.mccourse.item.ModItems

object MCCourse : ModInitializer {
	const val MOD_ID = "mccourse"
	private val logger: Logger = LoggerFactory.getLogger("MC Course")

	override fun onInitialize() {

		logger.info("Hello Fabric world!")

		ModItems.initialize(logger)
		ModItemGroups.initialize(logger)
		ModBlocks.initialize(logger)

		addItemsToGroups()

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600)

	}

	private fun addItemsToGroups() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register { entries: FabricItemGroupEntries ->
			entries.add(ModItems.FLUORITE)
			entries.add(ModItems.RAW_FLUORITE)
		}
	}
}