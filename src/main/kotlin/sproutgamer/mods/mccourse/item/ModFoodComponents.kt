package sproutgamer.mods.mccourse.item

import net.minecraft.component.type.ConsumableComponent
import net.minecraft.component.type.ConsumableComponents
import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.consume.ApplyEffectsConsumeEffect

class ModFoodComponents {
    companion object {
        val STRAWBERRY: FoodComponent = FoodComponent.Builder().nutrition(3).saturationModifier(0.25f).build()

        val STRAWBERRY_EFFECT: ConsumableComponent = ConsumableComponents.food()
            .consumeEffect(ApplyEffectsConsumeEffect(StatusEffectInstance(StatusEffects.LUCK, 200), 0.25f))
            .build()
    }
}