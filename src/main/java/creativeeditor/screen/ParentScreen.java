package creativeeditor.screen;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;

import creativeeditor.screen.widgets.StyledTextField;
import creativeeditor.styles.StyleManager;
import creativeeditor.util.ColorUtils.Color;
import creativeeditor.util.GuiUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public abstract class ParentScreen extends Screen {
    protected final Screen lastScreen;
    protected List<Widget> renderWidgets = Lists.newArrayList();

    @Getter
    @Setter
    private int topLineWidth = -1;


    public ParentScreen(ITextComponent title, Screen lastScreen) {
        super( title );
        this.lastScreen = lastScreen;
    }


    @Override
    protected void init() {
        super.init();
    }


    @Override
    public void resize( Minecraft mc, int width, int height ) {
        super.resize( mc, width, height );
    }


    @Override
    public void onClose() {
        minecraft.displayGuiScreen( lastScreen );
    }


    @Override
    public boolean keyPressed( int key1, int key2, int key3 ) {
        return super.keyPressed( key1, key2, key3 );
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }


    public Minecraft getMinecraftInstance() {
        return minecraft;
    }


    public FontRenderer getFontRenderer() {
        return font;
    }


    /**
     * Modified version of
     * {@link net.minecraft.client.gui.screen.inventory.ContainerScreen#drawItemStack}
     * 
     * Draws an ItemStack.
     * 
     * The z index is increased by 32 (and not decreased afterwards), and the item
     * is then rendered at z=200.
     */
    public void drawItemStack( ItemStack stack, int x, int y, String altText ) {
        RenderSystem.translatef( 0.0F, 0.0F, 32.0F );
        this.setBlitOffset( 200 );
        this.itemRenderer.zLevel = 200.0F;
        net.minecraft.client.gui.FontRenderer font = stack.getItem().getFontRenderer( stack );
        if (font == null)
            font = this.font;
        this.itemRenderer.renderItemAndEffectIntoGUI( stack, x, y );
        this.itemRenderer.renderItemOverlayIntoGUI( font, stack, x, y, altText );
        this.setBlitOffset( 0 );
        this.itemRenderer.zLevel = 0.0F;
    }


    @Override
    public void tick() {
        renderWidgets.forEach( w -> {
            if (w instanceof StyledTextField) {
                ((StyledTextField) w).tick();
            }
        } );
    }


    @Override
    public boolean mouseClicked( double mouseX, double mouseY, int mouseButton ) {
        for (Widget w : renderWidgets) {
            if (w.mouseClicked( mouseX, mouseY, mouseButton )) {
                this.setFocused( w );
                if (mouseButton == 0) {
                    this.setDragging( true );
                }

                return true;
            }
        }
        return super.mouseClicked( mouseX, mouseY, mouseButton );
    }


    @Override
    @Deprecated
    public void render( int mouseX, int mouseY, float partialTicks ) {
        Color color = StyleManager.getCurrentStyle().getMainColor();
        backRender( mouseX, mouseY, partialTicks, color );
        mainRender( mouseX, mouseY, partialTicks, color );
        overlayRender( mouseX, mouseY, partialTicks, color );
        StyleManager.getCurrentStyle().update();
    }


    public void backRender( int mouseX, int mouseY, float p3, Color color ) {
        StyleManager.getCurrentStyle().renderBackground( this );

        // Frame
        GuiUtil.drawFrame( 5, 5, width - 5, height - 5, 1, color );

        // GUI Title
        drawCenteredString( font, getTitle().getFormattedText(), width / 2, 9, color.getInt() );

        // Title underline
        int midX = width / 2;
        if (getTopLineWidth() != -1) {
            int sWidthHalf = font.getStringWidth( getTitle().getFormattedText() ) / 2 + 3;
            AbstractGui.fill( midX - sWidthHalf, 20, midX + sWidthHalf, 21, color.getInt() );

        }
        else {
            int halfLineW = topLineWidth / 2;
            AbstractGui.fill( midX - halfLineW, 20, midX + halfLineW, 21, color.getInt() );
        }
    }


    public void mainRender( int mouseX, int mouseY, float p3, Color color ) {
        buttons.forEach( b -> b.render( mouseX, mouseY, p3 ) );
        renderWidgets.forEach( w -> w.render( mouseX, mouseY, p3 ) );
    }


    /**
     * Should always be called last in render, but only once.
     */
    public void overlayRender( int mouseX, int mouseY, float p3, Color color ) {
    }
}
