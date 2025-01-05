package sproutgamer.mods.mccourse.event

import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import sproutgamer.mods.mccourse.item.ModItems

class AttackEntityHandler : AttackEntityCallback {
    override fun interact(attacker: PlayerEntity?, world: World?, hand: Hand?, victim: Entity?, hitResult: EntityHitResult?): ActionResult {
        if (victim is SheepEntity && world is ServerWorld) {
            when (attacker!!.mainHandStack?.item) {
                ModItems.METAL_DETECTOR -> attacker.sendMessage(Text.of("${attacker.name.string} tried metal detecting a sheep!"), false)

                ModItems.STRAWBERRY -> {
                    attacker.sendMessage(Text.of("${attacker.name.string} attacked Sheep with a strawberry?"), false)
                    victim.addStatusEffect(StatusEffectInstance(StatusEffects.LEVITATION, 400))
                    attacker.mainHandStack.decrement(1)
                }
            }
        }

        return ActionResult.PASS
    }
}