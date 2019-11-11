package creativeeditor.screen;

import creativeeditor.config.styles.ColorStyles;
import creativeeditor.config.styles.ColorStyles.Style;
import creativeeditor.util.ColorUtils.Color;
import creativeeditor.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

public class ParentScreen extends Screen {
	protected final Screen lastScreen;
	protected Minecraft mc;

	public ParentScreen(ITextComponent title, Screen lastScreen) {
		super(title);
		this.lastScreen = lastScreen;
		this.mc = Minecraft.getInstance();
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void resize(Minecraft mc, int width, int height) {
		super.resize(mc, width, height);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public boolean keyPressed(int key1, int key2, int key3) {
		return super.keyPressed(key1, key2, key3);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
	

	@Override
	@Deprecated
	public void render(int mouseX, int mouseY, float p3) {
		Color color = ColorStyles.getMainColor();
		backRender(mouseX, mouseY, p3, color);
		mainRender(mouseX, mouseY, p3, color);
		overlayRender(mouseX, mouseY, p3, color);
		ColorStyles.tickSpectrum();
	}

	public void backRender(int mouseX, int mouseY, float p3, Color color) {
		if (ColorStyles.getStyle() == Style.vanilla)
			renderBackground();
		else
			fillGradient(0, 0, width, height, -1072689136, -804253680);

		// Frame
		GuiUtils.drawFrame(5, 5, width - 5, height - 5, 1, color);

		// GUI Title
		drawCenteredString(font, getTitle().getFormattedText(), width / 2, 9, color.getColor());
		int sWidthHalf = font.getStringWidth(getTitle().getFormattedText()) / 2 + 3;
		
		// Title underline
		AbstractGui.fill(width / 2 - sWidthHalf, 20, width / 2 + sWidthHalf, 21, color.getColor());

	}

	public void mainRender(int mouseX, int mouseY, float p3, Color color) {

	}

	/**
	 * Should always be called last in render, but only once.
	 */
	public void overlayRender(int mouseX, int mouseY, float p3, Color color) {
	}
}