package sproutgamer.mods.mccourse.event

import net.minecraft.block.Blocks
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.LoreComponent
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Rarity
import net.minecraft.util.math.BlockPos
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradedItem
import net.minecraft.world.World
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.item.ModItems
import sproutgamer.mods.mccourse.util.ModUtils

class LuckyBlockEvents(private val world: World, private val player: PlayerEntity, private val pos: BlockPos) {

    fun triggerRandomEvent() {
        when (5) {
            0 -> jumpingPotion()
            1 -> stickOfJustice()
            2 -> superPickaxe()
            3 -> explosiveSnowballs()
            4 -> spawnZombie()
            5 -> mobStack()
            6 -> spawnPigs()
            7 -> dropAnvil()
            8 -> buildCreeperCage()
            9 -> spawnDog()
            10 -> wanderingTrader()
            11 -> superCrossbow()
        }
    }

    private fun superCrossbow() {
        val crossbow = ItemStack(Items.CROSSBOW)
        ModUtils.Enchantment.addEnchantmentsToItemStack(crossbow, world.registryManager,
            mapOf(Enchantments.UNBREAKING to 3, Enchantments.MULTISHOT to 1, Enchantments.QUICK_CHARGE to 5))
        crossbow.set(DataComponentTypes.ITEM_NAME, Text.translatable("block.mccourse.lucky_block.event.11.item"))
        crossbow.set(DataComponentTypes.RARITY, Rarity.EPIC)
        crossbow.set(DataComponentTypes.LORE, LoreComponent(listOf(Text.translatable("block.mccourse.lucky_block.event.11.item.lore"))))
        player.giveItemStack(crossbow)
    }

    private fun wanderingTrader() {
        if (!world.isClient) {
            val trader = EntityType.WANDERING_TRADER.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)
            if (trader != null) {
                trader.offers.clear()
                trader.offers.add(TradeOffer(
                    TradedItem(ModBlocks.LUCKY_BLOCK, 2),
                    ItemStack(ModItems.EXPLOSIVE_SNOWBALL, 4),
                    1,
                    1,
                    1f
                ))
                trader.offers.add(TradeOffer(
                    TradedItem(Items.EMERALD, 10),
                    ItemStack(ModBlocks.LUCKY_BLOCK, 4),
                    1,
                    1,
                    1f
                ))
            }
        }
    }

    private fun spawnDog() {
        if (!world.isClient) {
            val dog = EntityType.WOLF.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)
            if (dog != null) {
                dog.customName = Text.translatable("block.mccourse.lucky_block.event.9.dog")
                dog.setTamed(true, true)
                dog.setOwner(player)
            }
        }
    }

    private fun buildCreeperCage() {
        val wallCorner1 = player.blockPos.add(2, 3, 2)
        val wallCorner2 = player.blockPos.add(-2, -1, -2)
        val airCorner1 = player.blockPos.add(1, 2, 1)
        val airCorner2 = player.blockPos.add(-1, 0, -1)

        ModUtils.Block.fillAreaWithBlock(Blocks.SEA_LANTERN, wallCorner1, wallCorner2, world)
        ModUtils.Block.fillAreaWithBlock(Blocks.AIR, airCorner1, airCorner2, world)

        if (!world.isClient) {
            EntityType.CREEPER.spawn(world as ServerWorld?, player.blockPos, SpawnReason.TRIGGERED)
        }
    }

    private fun dropAnvil() {
        val anvilPos = player.blockPos.add(0, 30, 0)
        world.setBlockState(anvilPos, Blocks.ANVIL.defaultState)
    }

    private fun spawnPigs() {
        if (!world.isClient) {
            for (i in 0..50) {
                EntityType.PIG.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)
            }
        }
    }

    private fun mobStack() {
        if (!world.isClient) {
            val chicken1 = EntityType.CHICKEN.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)
            val chicken2 = EntityType.CHICKEN.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)
            val creeper = EntityType.CREEPER.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)

            // creeper?.startRiding(chicken2)
            // chicken2?.startRiding(chicken1)
            val entities = listOf(creeper as Entity, chicken2 as Entity, chicken1 as Entity)
            ModUtils.Entity.createEntityStack(entities)
        }
    }

    private fun spawnZombie() {
        if (!world.isClient) {
            val zombie = EntityType.ZOMBIE.spawn(world as ServerWorld?, pos, SpawnReason.TRIGGERED)

            if (zombie != null) {
                zombie.equipStack(EquipmentSlot.MAINHAND, ItemStack(Items.DIAMOND_SWORD))
                ModUtils.Item.equipArmorSet(zombie, ModBlocks.LUCKY_BLOCK, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS)
                zombie.customName = Text.translatable("block.mccourse.lucky_block.event.4.zombie")
            }
        }
    }

    private fun explosiveSnowballs() {
        player.giveItemStack(ItemStack(ModItems.EXPLOSIVE_SNOWBALL, 4))
    }

    private fun superPickaxe() {
        val pickaxe = ItemStack(ModItems.FLUORITE_PICKAXE)
        ModUtils.Enchantment.addEnchantmentsToItemStack(pickaxe, world.registryManager, mapOf(Enchantments.EFFICIENCY to 255))
        player.giveItemStack(pickaxe)
    }

    private fun stickOfJustice() {
        val stick = ItemStack(Items.STICK)
        ModUtils.Enchantment.addEnchantmentsToItemStack(stick, world.registryManager, mapOf(Enchantments.KNOCKBACK to 255))
        stick.set(DataComponentTypes.ITEM_NAME, Text.translatable("block.mccourse.lucky_block.event.1.item"))
        player.giveItemStack(stick)
    }

    private fun jumpingPotion() {
        player.addStatusEffect(StatusEffectInstance(StatusEffects.JUMP_BOOST, 1000, 5))
    }

}
