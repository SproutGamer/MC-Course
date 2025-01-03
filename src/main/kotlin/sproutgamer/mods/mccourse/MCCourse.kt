package sproutgamer.mods.mccourse

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.registry.FuelRegistryEvents
import net.minecraft.item.FuelRegistry
import net.minecraft.item.ItemGroups
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.event.HammerUsageEvent
import sproutgamer.mods.mccourse.item.ModItemGroups
import sproutgamer.mods.mccourse.item.ModItems

object MCCourse : ModInitializer {
	const val MOD_ID = "mccourse"
	private val logger: Logger = LoggerFactory.getLogger("MC Course")

	override fun onInitialize() {

		ModItems.initialize(logger)
		ModItemGroups.initialize(logger)
		ModBlocks.initialize(logger)

		addItemsToGroups()

		FuelRegistryEvents.BUILD.register { builder, _ ->
			builder.add(ModItems.STARLIGHT_ASHES, 600)
		}

		PlayerBlockBreakEvents.BEFORE.register(HammerUsageEvent())

	}

	private fun addItemsToGroups() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register { entries: FabricItemGroupEntries ->
			entries.add(ModItems.FLUORITE)
			entries.add(ModItems.RAW_FLUORITE)
		}
	}
}