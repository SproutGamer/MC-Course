package sproutgamer.mods.mccourse.item.type

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.ArmorMaterials
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.world.World
import sproutgamer.mods.mccourse.item.ModArmorMaterials

class ModArmorItem(material: ArmorMaterial?, type: EquipmentType?, settings: Settings?) : ArmorItem(material, type,
    settings
) {

    companion object {

        private val MATERIAL_TO_EFFECT_MAP = mapOf(
            ModArmorMaterials.FLUORITE to listOf(StatusEffectInstance(StatusEffects.HASTE, 1, 5, false, false),
                StatusEffectInstance(StatusEffects.SPEED, 1, 5, false, false))
        )

    }

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (!world?.isClient!! && entity is PlayerEntity && hasFullSuitOfArmorOn(entity)) {
            evaluateArmorEffects(entity)
        }

        super.inventoryTick(stack, world, entity, slot, selected)
    }

    private fun evaluateArmorEffects(player: PlayerEntity) {
        for (entry in MATERIAL_TO_EFFECT_MAP.entries) {
            val armorMaterial = entry.key
            val statusEffects = entry.value

            if (hasCorrectArmorOn(armorMaterial, player)) {
                addStatusEffectForMaterial(player, armorMaterial, statusEffects)
            }
        }
    }

    private fun hasFullSuitOfArmorOn(player: PlayerEntity): Boolean {
        val boots = player.inventory.getArmorStack(0)
        val leggings = player.inventory.getArmorStack(1)
        val chestplate = player.inventory.getArmorStack(2)
        val helmet = player.inventory.getArmorStack(3)

        return !boots.isEmpty && !leggings.isEmpty && !chestplate.isEmpty && !helmet.isEmpty
    }

    private fun hasCorrectArmorOn(material: ArmorMaterial, player: PlayerEntity): Boolean {
        for (armorStack in player.inventory.armor) {
            if (armorStack.item !is ArmorItem) {
                return false
            }
        }

        val boots = player.inventory.getArmorStack(0).item as ArmorItem
        val leggings = player.inventory.getArmorStack(1).item as ArmorItem
        val chestplate = player.inventory.getArmorStack(2).item as ArmorItem
        val helmet = player.inventory.getArmorStack(3).item as ArmorItem

        val bootsComponent = boots.components[DataComponentTypes.EQUIPPABLE]!!
        val leggingsComponent = leggings.components[DataComponentTypes.EQUIPPABLE]!!
        val chestplateComponent = chestplate.components[DataComponentTypes.EQUIPPABLE]!!
        val helmetComponent = helmet.components[DataComponentTypes.EQUIPPABLE]!!

        return bootsComponent.assetId.get() == material.assetId &&
                leggingsComponent.assetId.get() == material.assetId &&
                chestplateComponent.assetId.get() == material.assetId &&
                helmetComponent.assetId.get() == material.assetId
    }

    private fun addStatusEffectForMaterial(player: PlayerEntity, material: ArmorMaterial, effects: List<StatusEffectInstance>) {
        val playerHasEffect = effects.stream().allMatch { statusEffect -> player.hasStatusEffect(statusEffect.effectType) }

        if (!playerHasEffect) {
            for (effect in effects) {
                player.addStatusEffect(StatusEffectInstance(effect.effectType,
                    effect.duration, effect.amplifier, effect.isAmbient, effect.shouldShowParticles()))
            }
        }
    }

}