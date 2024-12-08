package sproutgamer.mods.mccourse

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroups
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sproutgamer.mods.mccourse.item.ModItems

object MCCourse : ModInitializer {
	const val MOD_ID = "mccourse"
	private val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {

		logger.info("Hello Fabric world!")

		addItemsToGroups()

	}

	private fun addItemsToGroups() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register { entries: FabricItemGroupEntries ->

			entries.add(ModItems.FLUORITE)
			entries.add(ModItems.RAW_FLUORITE)
		}
	}
}