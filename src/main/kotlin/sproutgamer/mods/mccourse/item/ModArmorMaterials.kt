package sproutgamer.mods.mccourse.item

import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.sound.SoundEvents
import sproutgamer.mods.mccourse.equipment.ModEquipmentAssetKeys
import sproutgamer.mods.mccourse.tag.ModTags

class ModArmorMaterials {

    companion object {

        val FLUORITE = ArmorMaterial(
            15,
            mapOf(
                EquipmentType.HELMET to 2,
                EquipmentType.CHESTPLATE to 6,
                EquipmentType.LEGGINGS to 4,
                EquipmentType.BOOTS to 2,
                EquipmentType.BODY to 4
            ),
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0f,
            0f,
            ModTags.Items.FLUORITE_REPAIR,
            ModEquipmentAssetKeys.FLUORITE
        )

    }

}