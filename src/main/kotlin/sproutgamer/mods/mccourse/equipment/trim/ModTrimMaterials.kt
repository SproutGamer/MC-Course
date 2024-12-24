package sproutgamer.mods.mccourse.equipment.trim

import net.minecraft.item.Item
import net.minecraft.item.equipment.trim.ArmorTrimMaterial
import net.minecraft.registry.Registerable
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.item.ModItems

class ModTrimMaterials {

    companion object {

        val FLUORITE: RegistryKey<ArmorTrimMaterial> = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(MCCourse.MOD_ID, "fluorite"))

        fun bootstrap(registerable: Registerable<ArmorTrimMaterial>) {
            register(registerable, FLUORITE, Registries.ITEM.getEntry(ModItems.FLUORITE), Style.EMPTY.withColor(0xB03FE0))
        }

        private fun register(
            registerable: Registerable<ArmorTrimMaterial>, armorTrimKey: RegistryKey<ArmorTrimMaterial>,
            item: RegistryEntry<Item>, style: Style
        ) {

            val trimMaterial = ArmorTrimMaterial(armorTrimKey.value.path, item, mapOf(), Text.translatable(
                Util.createTranslationKey("trim_material", armorTrimKey.value)
            ).fillStyle(style))

            registerable.register(armorTrimKey, trimMaterial)

        }

    }

}