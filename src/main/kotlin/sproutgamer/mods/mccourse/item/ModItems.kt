package sproutgamer.mods.mccourse.item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.item.type.ChainsawItem

class ModItems {
    companion object {
        val FLUORITE = registerItem("fluorite", Item(Item.Settings()))
        val RAW_FLUORITE = registerItem("raw_fluorite", Item(Item.Settings()))

        val CHAINSAW = registerItem("chainsaw", ChainsawItem(Item.Settings().maxDamage(255)))
        val STRAWBERRY = registerItem("strawberry", object : Item(Settings().food(ModFoodComponents.STRAWBERRY)) {
            override fun appendTooltip(
                stack: ItemStack?,
                context: TooltipContext?,
                tooltip: MutableList<Text>?,
                type: TooltipType?
            ) {
                tooltip?.add(Text.translatable("item.mccourse.strawberry.tooltip"))

                super.appendTooltip(stack, context, tooltip, type)
            }
        })

        val STARLIGHT_ASHES = registerItem("starlight_ashes", Item(Item.Settings()))

        private fun registerItem(name: String, item: Item): Item {
            return Registry.register(Registries.ITEM, Identifier.of(MCCourse.MOD_ID, name), item)
        }

        fun initialize(logger: Logger) {
            logger.info("Initializing Items...")
        }
    }
}