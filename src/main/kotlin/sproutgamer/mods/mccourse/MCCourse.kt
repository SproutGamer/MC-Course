package sproutgamer.mods.mccourse

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object MCCourse : ModInitializer {
	private const val MOD_ID = "mccourse"
	private val logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {

		logger.info("Hello Fabric world!")

	}
}