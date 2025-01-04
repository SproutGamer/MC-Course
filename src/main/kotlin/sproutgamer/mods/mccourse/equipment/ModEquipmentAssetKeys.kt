package sproutgamer.mods.mccourse.equipment

import net.minecraft.item.equipment.EquipmentAsset
import net.minecraft.item.equipment.EquipmentAssetKeys
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse

object ModEquipmentAssetKeys {

    val FLUORITE = registerEquipmentAssetKey("fluorite")

    private fun registerEquipmentAssetKey(name: String?): RegistryKey<EquipmentAsset> {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(MCCourse.ID, name))
    }

}