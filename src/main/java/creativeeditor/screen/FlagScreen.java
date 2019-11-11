package creativeeditor.screen;

import creativeeditor.nbt.NBTItemBase;
import creativeeditor.util.HideFlagUtils;
import creativeeditor.widgets.CEWButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class FlagScreen extends ParentScreen {

	public FlagScreen(ITextComponent title, Screen lastScreen, NBTItemBase editing) {
		super(new StringTextComponent(I18n.format("gui.itemflag")), lastScreen, editing);
		setRenderItem(false, 0f);
		
	}

	@Override
	protected void init() {
		//render Item
		renderItem = true;
		
		hasEssButtons = true;
		
		int thirdWidth = width/3;
		int amount = HideFlagUtils.Flags.values().length;
		for (int i = 0; i < amount; i++) {
			//later: make this a toggle button
			int x = (i<amount/2?thirdWidth:2*thirdWidth)-60; // 1/3 of width if i < 3, other 2/3 of width
			int y = height / 7 * 2 + (30 * (i<amount/2?i:i-amount/2)); //i*30, or (i-3)*30 if i>=3
			addButton(new CEWButton(x, y, 120, 20, I18n.format(HideFlagUtils.Flags.values()[i].getKey()), (Button b) -> {}));
		}
		addButton(new CEWButton(width / 2 - 60, height / 7 * 4, 120, 20, I18n.format("flag.switchall"), (Button b) -> {}));
		super.init();
	}

	@Override
	public void render(int mouseX, int mouseY, float p3) {
		super.render(mouseX, mouseY, p3);
	}
}
