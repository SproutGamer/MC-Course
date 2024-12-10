package sproutgamer.mods.mccourse.item

import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

class ModFoodComponents {
    companion object {
        val STRAWBERRY: FoodComponent = FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(StatusEffectInstance(StatusEffects.LUCK, 200), 0.25f).build()
    }
}