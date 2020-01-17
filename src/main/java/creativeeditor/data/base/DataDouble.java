package creativeeditor.data.base;

import net.minecraft.nbt.DoubleNBT;

public class DataDouble extends SingularData<Double, DoubleNBT> {
	public DataDouble(double value) {
		super(value);
	}
	
	public DataDouble(DoubleNBT nbt) {
		this(nbt.getDouble());
	}

	@Override
	public DataDouble copy() {
		return new DataDouble(data);
	}

	@Override
	public boolean isDefault() {
		return data == 0;
	}

	@Override
	public DoubleNBT getNBT() {
		return new DoubleNBT(data);
	}
}