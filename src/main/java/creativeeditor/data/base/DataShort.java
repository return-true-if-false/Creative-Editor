package creativeeditor.data.base;

import creativeeditor.data.Data;
import net.minecraft.nbt.ShortNBT;

public class DataShort extends SingularData<Short, ShortNBT> {
	public DataShort() {
		this((short) 0);
	}

	public DataShort(short value) {
		super(value);
	}

	public DataShort(ShortNBT nbt) {
		this(nbt.getShort());
	}

	@Override
	public Data<Short, ShortNBT> copy() {
		return new DataShort(data);
	}

	@Override
	public boolean isDefault() {
		return data == 0;
	}

	@Override
	public ShortNBT getNBT() {
		return new ShortNBT(data);
	}
}
