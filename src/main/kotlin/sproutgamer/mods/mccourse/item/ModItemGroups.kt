package sproutgamer.mods.mccourse.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse

class ModItemGroups {
    companion object {
        val FLUORITE_GROUP = registerItemGroup("fluorite", FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.mccourse.fluorite"))
            .icon { ItemStack(ModItems.FLUORITE) }
            .entries { displayContext, entries ->
                entries.add(ModItems.FLUORITE)
                entries.add(ModItems.RAW_FLUORITE)
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