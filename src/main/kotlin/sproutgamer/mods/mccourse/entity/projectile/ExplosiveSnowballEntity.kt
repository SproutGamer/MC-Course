package sproutgamer.mods.mccourse.entity.projectile

import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.thrown.SnowballEntity
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import net.minecraft.world.explosion.ExplosionBehavior

class ExplosiveSnowballEntity(world: World, private val player: PlayerEntity) : SnowballEntity(world, player) {

    override fun onBlockHit(blockHitResult: BlockHitResult?) {
        if (blockHitResult != null) {
            world.createExplosion(player, Explosion.createDamageSource(world, player), null, x, y, z, 5f, true, World.ExplosionSourceType.TRIGGER)
        }

        super.onBlockHit(blockHitResult)
    }

}
