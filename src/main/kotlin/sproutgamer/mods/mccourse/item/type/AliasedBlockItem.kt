package sproutgamer.mods.mccourse.item.type

import net.minecraft.block.Block
import net.minecraft.item.BlockItem

class AliasedBlockItem(block: Block?, settings: Settings?) : BlockItem(block, settings?.useItemPrefixedTranslationKey())