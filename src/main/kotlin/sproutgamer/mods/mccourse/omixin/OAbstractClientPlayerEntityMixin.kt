package sproutgamer.mods.mccourse.omixin

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.MathHelper
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import sproutgamer.mods.mccourse.item.ModItems

object OAbstractClientPlayerEntityMixin {

    @JvmStatic
    fun getFovMultiplierMixin(
        cir: CallbackInfoReturnable<Float>,
        f: Float,
        player: PlayerEntity
    ) {
        player.activeItem.item
        val stack = player.activeItem

        var float = f

        if (player.isUsingItem && stack.isOf(ModItems.KAUPEN_BOW)) {
            val i = player.itemUseTime
            var g = i / 20f

            g = if (g > 1f) 1f else g * g

            float *= 1f - g * .15f

            cir.returnValue = MathHelper.lerp(MinecraftClient.getInstance().options.fovEffectScale.value.toFloat(), 1f, float)
        }
    }

}