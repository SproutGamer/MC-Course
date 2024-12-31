package sproutgamer.mods.mccourse.datagen.loot

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Item
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootPoolEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import sproutgamer.mods.mccourse.block.ModBlocks
import sproutgamer.mods.mccourse.item.ModItems
import java.util.concurrent.CompletableFuture

class ModBlockLootTableProvider(dataOutput: FabricDataOutput?,
                                registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>?) : FabricBlockLootTableProvider(dataOutput, registryLookup) {

    override fun generate() {
        addDrop(ModBlocks.FLUORITE_BLOCK)
        addDrop(ModBlocks.MAGIC_BLOCK)

        addDrop(ModBlocks.FLUORITE_ORE, variableOreDrops(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE, 4f, 7f))
        addDrop(ModBlocks.DEEPSLATE_FLUORITE_ORE, variableOreDrops(ModBlocks.DEEPSLATE_FLUORITE_ORE, ModItems.RAW_FLUORITE, 4f, 7f))
        addDrop(ModBlocks.NETHER_FLUORITE_ORE, variableOreDrops(ModBlocks.DEEPSLATE_FLUORITE_ORE, ModItems.RAW_FLUORITE, 5f, 8f))
        addDrop(ModBlocks.END_FLUORITE_ORE, variableOreDrops(ModBlocks.DEEPSLATE_FLUORITE_ORE, ModItems.RAW_FLUORITE, 6f, 9f))

        addDrop(ModBlocks.FLUORITE_STAIRS)
        addDrop(ModBlocks.FLUORITE_SLAB, slabDrops(ModBlocks.FLUORITE_SLAB))
        addDrop(ModBlocks.FLUORITE_BUTTON)
        addDrop(ModBlocks.FLUORITE_PRESSURE_PLATE)
        addDrop(ModBlocks.FLUORITE_FENCE)
        addDrop(ModBlocks.FLUORITE_FENCE_GATE)
        addDrop(ModBlocks.FLUORITE_WALL)
        addDrop(ModBlocks.FLUORITE_DOOR, doorDrops(ModBlocks.FLUORITE_DOOR))
        addDrop(ModBlocks.FLUORITE_TRAPDOOR)

        addDrop(ModBlocks.FLUORITE_LAMP)

    }

    private fun variableOreDrops(drop: Block?, item: Item, minDrops: Float, maxDrops: Float): LootTable.Builder {
        val impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT)
        return this.dropsWithSilkTouch(
            drop,
            applyExplosionDecay(
                drop,
                ItemEntry.builder(item)
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))
                    .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
            ) as LootPoolEntry.Builder<*>
        )
    }

    override fun getName(): String {
        return "MC Course Block Loot Table Provider"
    }
}