package sproutgamer.mods.mccourse.item

import net.minecraft.item.ToolMaterial
import sproutgamer.mods.mccourse.tag.ModTags

class ModToolMaterial {
    companion object {
        val FLUORITE =
            ToolMaterial(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL, 1500, 7f, 2f, 22, ModTags.Items.FLUORITE_REPAIR)
    }
}
