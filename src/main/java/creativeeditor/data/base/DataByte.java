package creativeeditor.data.base;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class DataByte extends SingularData<Byte, ByteNBT> {
    public DataByte(byte value) {
        super(value);
    }


    public DataByte(ByteNBT nbt) {
        this(nbt.getAsByte());
    }


    @Override
    public boolean isDefault() {
        return data == 0;
    }


    @Override
    public ByteNBT getNBT() {
        return ByteNBT.valueOf(data);
    }

    @Override
    public ITextComponent getPrettyDisplay(String space, int indentation) {
        ITextComponent itextcomponent = (new StringTextComponent("b")).withStyle(SYNTAX_HIGHLIGHTING_NUMBER_TYPE);
        return (new StringTextComponent(String.valueOf((int)this.data))).append(itextcomponent).withStyle(SYNTAX_HIGHLIGHTING_NUMBER);
    }
}
