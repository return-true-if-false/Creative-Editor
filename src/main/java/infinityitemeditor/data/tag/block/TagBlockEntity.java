package infinityitemeditor.data.tag.block;

import infinityitemeditor.data.Data;
import infinityitemeditor.data.DataItem;
import infinityitemeditor.data.base.DataString;
import infinityitemeditor.data.version.NBTKeys;
import lombok.Getter;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

public class TagBlockEntity implements Data<TagBlockEntity, CompoundNBT> {

    private final @Getter
    DataString locked;


    public TagBlockEntity(DataItem item, CompoundNBT nbt) {
        NBTKeys keys = NBTKeys.keys;
        locked = new DataString(nbt.getString(keys.locked()));
    }

    @Override
    public TagBlockEntity getData() {
        return this;
    }

    @Override
    public boolean isDefault() {
        return locked.isDefault();
    }

    @Override
    public CompoundNBT getNBT() {
        NBTKeys keys = NBTKeys.keys;
        CompoundNBT nbt = new CompoundNBT();
        if (!locked.isDefault())
            nbt.put(keys.locked(), locked.getNBT());
        return nbt;
    }

    @Override
    public ITextComponent getPrettyDisplay(String space, int indentation) {
        return getNBT().getPrettyDisplay(space, indentation);
    }
}