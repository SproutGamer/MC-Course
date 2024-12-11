package sproutgamer.mods.mccourse.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse

class ModTags {
    class Blocks {
        companion object {
            private fun createTag(name: String): TagKey<Block> {
                return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MCCourse.MOD_ID, name))
            }
        }
    }

    class Items {
        companion object {
            val TRANSFORMABLE_ITEMS = createTag("transformable_items")

            private fun createTag(name: String): TagKey<Item> {
                return TagKey.of(RegistryKeys.ITEM, Identifier.of(MCCourse.MOD_ID, name))
            }
        }
    }
}