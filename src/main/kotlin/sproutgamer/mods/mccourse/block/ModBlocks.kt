package sproutgamer.mods.mccourse.block

import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import org.slf4j.Logger
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.block.type.MagicBlock

class ModBlocks {
    companion object {
        val FLUORITE_BLOCK = registerBlock("fluorite_block", Block(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .strength(4f)
            .requiresTool()))

        val FLUORITE_ORE = registerBlock("fluorite_ore", ExperienceDroppingBlock(UniformIntProvider.create(2, 4), AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool()))
        val DEEPSLATE_FLUORITE_ORE = registerBlock("deepslate_fluorite_ore", ExperienceDroppingBlock(UniformIntProvider.create(2, 4), AbstractBlock.Settings.create()
            .strength(6f)
            .requiresTool()))
        val NETHER_FLUORITE_ORE = registerBlock("nether_fluorite_ore", ExperienceDroppingBlock(UniformIntProvider.create(2, 4), AbstractBlock.Settings.create()
            .strength(6f)
            .requiresTool()))
        val END_FLUORITE_ORE = registerBlock("end_fluorite_ore", ExperienceDroppingBlock(UniformIntProvider.create(2, 4), AbstractBlock.Settings.create()
            .strength(6f)
            .requiresTool()))

        val MAGIC_BLOCK = registerBlock("magic_block", MagicBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)))

        val FLUORITE_STAIRS = registerBlock("fluorite_stairs", StairsBlock(
            FLUORITE_BLOCK.defaultState, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()))
        val FLUORITE_SLAB = registerBlock("fluorite_slab", SlabBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()))
        val FLUORITE_BUTTON = registerBlock("fluorite_button", ButtonBlock(BlockSetType.IRON, 10,
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool()))
        val FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate", PressurePlateBlock(BlockSetType.IRON,
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool()))
        val FLUORITE_FENCE = registerBlock("fluorite_fence", FenceBlock(
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool()))
        val FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate", FenceGateBlock(WoodType.ACACIA,
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool()))
        val FLUORITE_WALL = registerBlock("fluorite_wall", WallBlock(
            AbstractBlock.Settings.create().strength(2f)
                .requiresTool()))


        private fun registerBlock(name: String, block: Block): Block {
            registerBlockItem(name, block)
            return Registry.register(Registries.BLOCK, Identifier.of(MCCourse.MOD_ID, name), block)
        }

        private fun registerBlockItem(name: String, block: Block) {
            val blockItem = BlockItem(block, Item.Settings())
            Registry.register(Registries.ITEM, Identifier.of(MCCourse.MOD_ID, name), blockItem)
        }

        fun initialize(logger: Logger) {
            logger.info("Initializing Blocks...")
        }
    }
}