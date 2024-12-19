package sproutgamer.mods.mccourse.util

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ModUtils {

    class Item {
        companion object {

            fun equipArmorSet(entity: LivingEntity, head: ItemConvertible?, chest: ItemConvertible?, legs: ItemConvertible?, feet: ItemConvertible?) {
                if (head != null) {
                    entity.equipStack(EquipmentSlot.HEAD, ItemStack(head))
                }
                if (chest != null) {
                    entity.equipStack(EquipmentSlot.CHEST, ItemStack(chest))
                }
                if (legs != null) {
                    entity.equipStack(EquipmentSlot.LEGS, ItemStack(legs))
                }
                if (feet != null) {
                    entity.equipStack(EquipmentSlot.FEET, ItemStack(feet))
                }
            }

        }
    }

    class Enchantment {
        companion object {

            fun addEnchantmentsToItemStack(stack: ItemStack, registryManager: DynamicRegistryManager,
                                           enchantmentToLevel: Map<RegistryKey<net.minecraft.enchantment.Enchantment>, Int>) {
                for (enchantmentEntry in enchantmentToLevel) {
                    val enchantment = registryManager.getOrThrow(RegistryKeys.ENCHANTMENT).get(enchantmentEntry.key)
                    val level = enchantmentEntry.value
                    stack.addEnchantment(RegistryEntry.of(enchantment), level)
                }
            }

        }
    }

    class Block {
        companion object {
            fun fillAreaWithBlock(block: net.minecraft.block.Block, corner1: BlockPos, corner2: BlockPos, world: World) {
                val minX = minOf(corner1.x, corner2.x)
                val minY = minOf(corner1.y, corner2.y)
                val minZ = minOf(corner1.z, corner2.z)
                val maxX = maxOf(corner1.x, corner2.x)
                val maxY = maxOf(corner1.y, corner2.y)
                val maxZ = maxOf(corner1.z, corner2.z)

                for (x in minX..maxX) {
                    for (y in minY..maxY) {
                        for (z in minZ..maxZ) {
                            val pos = BlockPos(x, y, z)
                            world.setBlockState(pos, block.defaultState)
                        }
                    }
                }
            }
        }

    }

    class Entity {
        companion object {
            fun createEntityStack(entities: List<net.minecraft.entity.Entity>) {
                for (i in entities.indices) {
                    if (entities[i] != entities.last()) {
                        entities[i].startRiding(entities[i + 1])
                    }
                }
            }
        }
    }

}
