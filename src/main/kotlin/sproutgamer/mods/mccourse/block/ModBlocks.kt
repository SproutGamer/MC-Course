package sproutgamer.mods.mccourse.block

import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.block.type.LuckyBlock
import sproutgamer.mods.mccourse.block.type.MagicBlock

class ModBlocks {
    companion object {
        val FLUORITE_BLOCK = registerBlock("fluorite_block", ::Block, AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .strength(4f)
            .requiresTool())

        val FLUORITE_ORE = registerBlock("fluorite_ore", { settings -> ExperienceDroppingBlock(UniformIntProvider.create(2, 4), settings) }, AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool())
        val DEEPSLATE_FLUORITE_ORE = registerBlock("deepslate_fluorite_ore",
            { settings -> ExperienceDroppingBlock(UniformIntProvider.create(2, 4), settings) }, AbstractBlock.Settings.create()
            .strength(6f)
            .requiresTool())
        val NETHER_FLUORITE_ORE = registerBlock("nether_fluorite_ore", { settings -> ExperienceDroppingBlock(UniformIntProvider.create(2, 4),
            settings) }, AbstractBlock.Settings.copy(DEEPSLATE_FLUORITE_ORE))
        val END_FLUORITE_ORE = registerBlock("end_fluorite_ore", { settings -> ExperienceDroppingBlock(UniformIntProvider.create(2, 4),
            settings) }, AbstractBlock.Settings.copy(DEEPSLATE_FLUORITE_ORE))

        val MAGIC_BLOCK = registerBlock("magic_block", ::MagicBlock, AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK))

        val FLUORITE_STAIRS = registerBlock("fluorite_stairs", { settings -> StairsBlock(
            FLUORITE_BLOCK.defaultState, settings) }, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool())
        val FLUORITE_SLAB = registerBlock("fluorite_slab", ::SlabBlock, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool())
        val FLUORITE_BUTTON = registerBlock("fluorite_button", { settings -> ButtonBlock(BlockSetType.IRON, 10, settings)},
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool())
        val FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate", { settings -> PressurePlateBlock(BlockSetType.IRON, settings) },
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool())
        val FLUORITE_FENCE = registerBlock("fluorite_fence", ::FenceBlock,
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool())
        val FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate", { settings -> FenceGateBlock(WoodType.ACACIA, settings) },
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool())
        val FLUORITE_WALL = registerBlock("fluorite_wall", ::WallBlock,
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool())
        val FLUORITE_DOOR = registerBlock("fluorite_door", { settings -> DoorBlock(BlockSetType.IRON, settings) },
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool().nonOpaque())
        val FLUORITE_TRAPDOOR = registerBlock("fluorite_trapdoor", { settings -> TrapdoorBlock(BlockSetType.IRON, settings) },
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool().nonOpaque())

        val LUCKY_BLOCK = registerBlock("lucky_block", ::LuckyBlock, AbstractBlock.Settings.copy(Blocks.GLASS))


        private fun registerBlock(path: String, factory: (AbstractBlock.Settings) -> Block, settings: AbstractBlock.Settings): Block {
            val id = Identifier.of(MCCourse.MOD_ID, path)
            val registryKey = RegistryKey.of(RegistryKeys.BLOCK, id)

            val block = Blocks.register(registryKey, factory, settings)
            Items.register(block)
            return block
        }

        fun initialize(logger: Logger) {
            logger.info("Initializing Blocks...")
        }
    }
}