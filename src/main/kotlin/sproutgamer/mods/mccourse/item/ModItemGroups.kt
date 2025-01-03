package sproutgamer.mods.mccourse.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.block.ModBlocks

class ModItemGroups {
    companion object {
        val FLUORITE_GROUP = registerItemGroup("fluorite", FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.mccourse.fluorite"))
            .icon { ItemStack(ModItems.FLUORITE) }
            .entries { _, entries ->
                entries.add(ModItems.FLUORITE)
                entries.add(ModItems.RAW_FLUORITE)
                entries.add(ModBlocks.FLUORITE_BLOCK)
                entries.add(ModBlocks.FLUORITE_ORE)
                entries.add(ModBlocks.DEEPSLATE_FLUORITE_ORE)
                entries.add(ModBlocks.NETHER_FLUORITE_ORE)
                entries.add(ModBlocks.END_FLUORITE_ORE)

                entries.add(ModItems.CHAINSAW)
                entries.add(ModBlocks.MAGIC_BLOCK)
                entries.add(ModItems.STRAWBERRY)
                entries.add(ModItems.STARLIGHT_ASHES)

                entries.add(ModBlocks.FLUORITE_STAIRS)
                entries.add(ModBlocks.FLUORITE_SLAB)
                entries.add(ModBlocks.FLUORITE_BUTTON)
                entries.add(ModBlocks.FLUORITE_PRESSURE_PLATE)
                entries.add(ModBlocks.FLUORITE_FENCE)
                entries.add(ModBlocks.FLUORITE_FENCE_GATE)
                entries.add(ModBlocks.FLUORITE_WALL)
                entries.add(ModBlocks.FLUORITE_DOOR)
                entries.add(ModBlocks.FLUORITE_TRAPDOOR)

                entries.add(ModItems.FLUORITE_SWORD)
                entries.add(ModItems.FLUORITE_PICKAXE)
                entries.add(ModItems.FLUORITE_SHOVEL)
                entries.add(ModItems.FLUORITE_AXE)
                entries.add(ModItems.FLUORITE_HOE)
                entries.add(ModItems.FLUORITE_PAXEL)
                entries.add(ModItems.FLUORITE_HAMMER)

                entries.add(ModItems.FLUORITE_HELMET)
                entries.add(ModItems.FLUORITE_CHESTPLATE)
                entries.add(ModItems.FLUORITE_LEGGINGS)
                entries.add(ModItems.FLUORITE_BOOTS)

                entries.add(ModItems.FLUORITE_HORSE_ARMOR)

                entries.add(ModBlocks.LUCKY_BLOCK)
                entries.add(ModItems.EXPLOSIVE_SNOWBALL)
            }
            .build())


        private fun registerItemGroup(name: String, itemGroup: ItemGroup): ItemGroup {
            return Registry.register(Registries.ITEM_GROUP, Identifier.of(MCCourse.MOD_ID, name), itemGroup)
        }

        fun initialize(logger: Logger) {
            logger.info("Initializing Item Groups...")
        }
    }
}