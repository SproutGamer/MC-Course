package sproutgamer.mods.mccourse.entity.projectile

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.thrown.SnowballEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World
import net.minecraft.world.explosion.AdvancedExplosionBehavior
import net.minecraft.world.explosion.Explosion
import sproutgamer.mods.mccourse.tag.ModTags
import sproutgamer.mods.mccourse.util.ModUtils
import java.util.*

class ExplosiveSnowballEntity(world: World, private val player: PlayerEntity, stack: ItemStack) : SnowballEntity(world, player, stack) {

    override fun onBlockHit(blockHitResult: BlockHitResult?) {
        if (blockHitResult != null) {
            val immuneBlocks: RegistryEntryList<Block> = RegistryEntryList.of(RegistryEntry.of(Blocks.OBSIDIAN), RegistryEntry.of(Blocks.BEDROCK))

            val behavior = AdvancedExplosionBehavior(true, true, Optional.of(0.5f), Optional.of(immuneBlocks))
            world.createExplosion(player, Explosion.createDamageSource(world, player), behavior, x, y, z, 5f, true, World.ExplosionSourceType.TRIGGER)
        }

        super.onBlockHit(blockHitResult)
    }

}
