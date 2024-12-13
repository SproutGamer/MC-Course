package sproutgamer.mods.mccourse.item

import net.minecraft.item.*
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.item.type.ChainsawItem
import sproutgamer.mods.mccourse.item.type.ExplosiveSnowballItem

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

        val FLUORITE_SWORD = registerItem("fluorite_sword", SwordItem(ModToolMaterials.FLUORITE,
            Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 8, -2.4f))))
        val FLUORITE_PICKAXE = registerItem("fluorite_pickaxe", PickaxeItem(ModToolMaterials.FLUORITE,
            Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 6f, -2.8f))))
        val FLUORITE_SHOVEL = registerItem("fluorite_shovel", ShovelItem(ModToolMaterials.FLUORITE,
            Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 6.5f, -3f))))
        val FLUORITE_AXE = registerItem("fluorite_axe", AxeItem(ModToolMaterials.FLUORITE,
            Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 11f, -3.2f))))
        val FLUORITE_HOE = registerItem("fluorite_hoe", HoeItem(ModToolMaterials.FLUORITE,
            Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 5f, -3f))))

        val EXPLOSIVE_SNOWBALL = registerItem("explosive_snowball", ExplosiveSnowballItem(Item.Settings().rarity(Rarity.RARE)))

        private fun registerItem(name: String, item: Item): Item {
            return Registry.register(Registries.ITEM, Identifier.of(MCCourse.MOD_ID, name), item)
        }

        fun initialize(logger: Logger) {
            logger.info("Initializing Items...")
        }
    }
}