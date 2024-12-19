package sproutgamer.mods.mccourse.item.type

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SnowballItem
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import sproutgamer.mods.mccourse.entity.projectile.ExplosiveSnowballEntity

class ExplosiveSnowballItem(settings: Settings?) : SnowballItem(settings) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): ActionResult {
        val itemStack = user!!.getStackInHand(hand)
        world!!.playSound(
            null,
            user.x,
            user.y,
            user.z,
            SoundEvents.ENTITY_SNOWBALL_THROW,
            SoundCategory.NEUTRAL,
            0.5f,
            0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f)
        )
        if (!world.isClient) {
            val snowballEntity = ExplosiveSnowballEntity(world, user, itemStack)
            snowballEntity.setItem(itemStack)
            snowballEntity.setVelocity(user, user.pitch, user.yaw, 0.0f, 1.5f, 1.0f)
            world.spawnEntity(snowballEntity)
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this))
        itemStack.decrementUnlessCreative(1, user)
        return ActionResult.SUCCESS
    }

    override fun hasGlint(stack: ItemStack?): Boolean {
        return true
    }

}