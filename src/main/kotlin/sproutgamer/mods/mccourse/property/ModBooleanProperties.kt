package sproutgamer.mods.mccourse.property

import net.minecraft.client.render.item.property.bool.BooleanProperties
import net.minecraft.util.Identifier
import sproutgamer.mods.mccourse.MCCourse
import sproutgamer.mods.mccourse.property.type.OnProperty

object ModBooleanProperties {

    fun bootstrap() {
        BooleanProperties.ID_MAPPER.put(Identifier.of(MCCourse.ID, "on"), OnProperty.CODEC)
    }

}