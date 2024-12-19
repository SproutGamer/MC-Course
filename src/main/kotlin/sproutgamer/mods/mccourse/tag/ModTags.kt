package sproutgamer.mods.mccourse.tag

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse

class ModTags {
    class Blocks {
        companion object {
            val NEEDS_FLUORITE_TOOL = createTag("needs_fluorite_tool")
            val INCORRECT_FOR_FLUORITE_TOOL = createTag("incorrect_for_fluorite_tool")


            private fun createTag(name: String): TagKey<Block> {
                return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MCCourse.MOD_ID, name))
            }
        }
    }

    class Items {
        companion object {
            val TRANSFORMABLE_ITEMS = createTag("transformable_items")
            val FLUORITE_REPAIR = createTag("fluorite_repair")


            private fun createTag(name: String): TagKey<Item> {
                return TagKey.of(RegistryKeys.ITEM, Identifier.of(MCCourse.MOD_ID, name))
            }
        }
    }
}