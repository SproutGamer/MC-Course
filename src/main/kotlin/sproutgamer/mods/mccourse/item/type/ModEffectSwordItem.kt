package sproutgamer.mods.mccourse.item.type

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.AttributeContainer
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.world.World

class ModEffectSwordItem(material: ToolMaterial?, attackDamage: Float, attackSpeed: Float, settings: Settings?,
                         private val effect: RegistryEntry<StatusEffect>
) : SwordItem(material, attackDamage,
    attackSpeed,
    settings
) {

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        target?.addStatusEffect(StatusEffectInstance(effect, 200, 1), attacker)
        return super.postHit(stack, target, attacker)
    }

}