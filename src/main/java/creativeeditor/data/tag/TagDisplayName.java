package creativeeditor.data.tag;

import creativeeditor.data.DataItem;
import creativeeditor.data.base.DataTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TagDisplayName extends DataTextComponent {
	private DataItem item;
	public TagDisplayName(DataItem item) {
		super(new StringTextComponent(item.getItemStack().getDisplayName().getFormattedText()));
		this.item = item;
	}

	@Override
	public boolean isDefault() {
		if(getUnformatted().length() == 0) {
			return true;
		}
		DataItem copy = item.copy();
		copy.clearCustomName();
		return item.getItemStack().getDisplayName().getFormattedText().equals(copy.getItemStack().getDisplayName().getFormattedText());
	}

	public void reset() {
		set(getDefault());
	}
	
	public ITextComponent getDefault() {
		DataItem copy = item.copy();
		copy.clearCustomName();
		return new StringTextComponent(copy.getItemStack().getDisplayName().getFormattedText());
	}
}