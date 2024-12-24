package sproutgamer.mods.mccourse.item

import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.*
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.item.type.*

class ModItems {
    companion object {
        val FLUORITE = registerItem("fluorite", ::Item, Item.Settings())
        val RAW_FLUORITE = registerItem("raw_fluorite", ::Item, Item.Settings())

        val CHAINSAW = registerItem("chainsaw", ::ChainsawItem, Item.Settings().maxDamage(255))
        val STRAWBERRY = registerItem("strawberry", { settings -> object : Item(settings) {
            override fun appendTooltip(
                stack: ItemStack?,
                context: TooltipContext?,
                tooltip: MutableList<Text>?,
                type: TooltipType?
            ) {
                tooltip?.add(Text.translatable("item.mccourse.strawberry.tooltip"))

                super.appendTooltip(stack, context, tooltip, type)
            }
        } }, Item.Settings().food(ModFoodComponents.STRAWBERRY, ModFoodComponents.STRAWBERRY_EFFECT))

        val STARLIGHT_ASHES = registerItem("starlight_ashes", ::Item, Item.Settings())

        val FLUORITE_SWORD = registerItem(
            "fluorite_sword", { settings -> ModEffectSwordItem(ModToolMaterial.FLUORITE, 8f, -2.4f, settings,
                StatusEffects.LEVITATION) },
            Item.Settings()
        )
        val FLUORITE_PICKAXE = registerItem(
            "fluorite_pickaxe", { settings -> PickaxeItem(ModToolMaterial.FLUORITE, 6f, -2.8f, settings) },
            Item.Settings()
        )
        val FLUORITE_SHOVEL = registerItem(
            "fluorite_shovel", { settings -> ShovelItem(ModToolMaterial.FLUORITE, 6.5f, -3f, settings) },
            Item.Settings()
        )
        val FLUORITE_AXE = registerItem(
            "fluorite_axe", { settings -> AxeItem(ModToolMaterial.FLUORITE, 11f, -3.2f, settings) },
            Item.Settings()
        )
        val FLUORITE_HOE = registerItem(
            "fluorite_hoe", { settings -> HoeItem(ModToolMaterial.FLUORITE, 0f , -3f, settings) },
            Item.Settings()
        )

        val EXPLOSIVE_SNOWBALL = registerItem(
            "explosive_snowball", ::ExplosiveSnowballItem, Item.Settings().rarity(Rarity.RARE)
        )

        val FLUORITE_PAXEL = registerItem(
            "fluorite_paxel", { settings -> PaxelItem(ModToolMaterial.FLUORITE, 6.3f, -2.88f, settings) },
            Item.Settings()
        )

        val FLUORITE_HAMMER = registerItem(
            "fluorite_hammer", { settings -> HammerItem(ModToolMaterial.FLUORITE, 9f, -3.9f, settings) },
            Item.Settings()
        )

        val FLUORITE_HELMET = registerItem("fluorite_helmet", { settings -> ModArmorItem(ModArmorMaterials.FLUORITE, EquipmentType.HELMET, settings) },
            Item.Settings())
        val FLUORITE_CHESTPLATE = registerItem("fluorite_chestplate", { settings -> ModArmorItem(ModArmorMaterials.FLUORITE, EquipmentType.CHESTPLATE, settings) },
            Item.Settings())
        val FLUORITE_LEGGINGS = registerItem("fluorite_leggings", { settings -> ModArmorItem(ModArmorMaterials.FLUORITE, EquipmentType.LEGGINGS, settings) },
            Item.Settings())
        val FLUORITE_BOOTS = registerItem("fluorite_boots", { settings -> ModArmorItem(ModArmorMaterials.FLUORITE, EquipmentType.BOOTS, settings) },
            Item.Settings())

        val FLUORITE_HORSE_ARMOR = registerItem("fluorite_horse_armor", { settings -> AnimalArmorItem(ModArmorMaterials.FLUORITE, AnimalArmorItem.Type.EQUESTRIAN, settings) }, Item.Settings())

        val KAUPEN_SMITHING_TEMPLATE = registerItem("kaupen_armor_trim_smithing_template", { settings -> SmithingTemplateItem.of(settings) }, Item.Settings())

        private fun registerItem(path: String, factory: (Item.Settings) -> Item, settings: Item.Settings): Item {
            val id = Identifier.of(MCCourse.MOD_ID, path)
            val key = RegistryKey.of(RegistryKeys.ITEM, id)

            val item = Items.register(key, factory, settings)
            return item
        }

        fun initialize(logger: Logger) {
            logger.info("Initializing Items...")
        }
    }
}