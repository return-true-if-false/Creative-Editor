package creativeeditor.mixin;

import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

@Mixin({TextFormatting.class})
public class TextFormattingMixin {

    @Overwrite
    public static String stripFormatting(@Nullable String string) { // func_110646_a
        return string;
    }
}