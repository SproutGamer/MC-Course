package sproutgamer.mods.mccourse.item

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse

object ModItems {

    val FLUORITE = registerItem("fluorite", Item(Item.Settings()))
    val RAW_FLUORITE = registerItem("raw_fluorite", Item(Item.Settings()))

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier.of(MCCourse.MOD_ID, name), item)
    }


}