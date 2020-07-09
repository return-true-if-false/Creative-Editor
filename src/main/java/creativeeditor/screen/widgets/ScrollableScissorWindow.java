package creativeeditor.screen.widgets;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import creativeeditor.styles.StyleManager;
import creativeeditor.util.GuiUtil;
import creativeeditor.util.RenderUtil;
import lombok.Getter;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.INestedGuiEventHandler;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.math.MathHelper;

public class ScrollableScissorWindow extends Widget implements INestedGuiEventHandler {
    @Nullable
    private IGuiEventListener focused;
    private boolean isDragging;

    private boolean isScrolling = false;
    private int scrollOffset = 0;
    private int padding = 5;
    private int scrollBarWidth = 10;

    @Getter
    protected final List<Widget> widgets = Lists.newArrayList();


    public ScrollableScissorWindow(int x, int y, int width, int height, String msg) {
        super( x, y, width, height, msg );
    }


    public int getListHeight() {
        int height = 5;
        for (Widget w : widgets) {
            height += w.getHeight() + padding;
        }
        return height;
    }


    @Override
    public List<? extends IGuiEventListener> children() {
        return widgets;
    }


    @Override
    public final boolean isDragging() {
        return this.isDragging;
    }


    @Override
    public final void setDragging( boolean isDragging ) {
        this.isDragging = isDragging;
    }


    @Nullable
    @Override
    public IGuiEventListener getFocused() {
        return this.focused;
    }


    @Override
    public void setFocused( IGuiEventListener focused ) {
        this.focused = focused;
    }


    @Override
    public boolean mouseClicked( double mouseX, double mouseY, int mouseButton ) {
        if (mouseButton == 0 && GuiUtil.isMouseInRegion( mouseX, mouseY, x + width - scrollBarWidth, y, scrollBarWidth, height )) {
            isScrolling = true;

            int listHeight = getListHeight();
            int max = listHeight - this.height;
            int insideHeight = (this.height - 4);
            double covered = MathHelper.clamp( insideHeight / (float) listHeight, 0d, 1d );
            int scrollBarHeight = (int) (insideHeight * covered);
            int scrollSpace = insideHeight - scrollBarHeight;
            double mousePos = MathHelper.clamp( mouseY - (this.y + 2 + (scrollBarHeight / 2)), 0, scrollSpace );
            double scrollPerc = mousePos / scrollSpace;
            scrollOffset = (int) (scrollPerc * max);
            return true;
        }
        else {
            isScrolling = false;
        }
        return super.mouseClicked( mouseX, mouseY, mouseButton );
    }


    @Override
    public boolean mouseReleased( double mouseX, double mouseY, int mouseButton ) {
        isScrolling = false;
        return super.mouseReleased( mouseX, mouseY, mouseButton );
    }


    @Override
    public boolean mouseDragged( double mouseX, double mouseY, int button, double onX, double onY ) {
        if (isScrolling) {
            int listHeight = getListHeight();
            int max = listHeight - this.height;
            int insideHeight = (this.height - 4);
            double covered = MathHelper.clamp( insideHeight / (float) listHeight, 0d, 1d );
            int scrollBarHeight = (int) (insideHeight * covered);
            int scrollSpace = insideHeight - scrollBarHeight;
            double mousePos = MathHelper.clamp( mouseY - (this.y + 2 + (scrollBarHeight / 2)), 0, scrollSpace );
            double scrollPerc = mousePos / scrollSpace;
            scrollOffset = (int) (scrollPerc * max);
        }
        return super.mouseDragged( mouseX, mouseY, button, onX, onY );
    }


    @Override
    public void renderButton( int mouseX, int mouseY, float partialTicks ) {
        // super.renderButton( mouseX, mouseY, partialTicks );
        // Minecraft mc = Minecraft.getInstance();
        GuiUtil.drawFrame( x, y, x + width, y + height, 1, StyleManager.getCurrentStyle().getMainColor() );
        int listHeight = getListHeight();
        GuiUtil.drawFrame( x + width - scrollBarWidth, y, x + width, y + height, 1, StyleManager.getCurrentStyle().getMainColor() );
        int insideHeight = (this.height - 4);
        double covered = MathHelper.clamp( insideHeight / (float) listHeight, 0d, 1d );
        int scrollBarHeight = (int) (insideHeight * covered);
        double scrolled = MathHelper.clamp( scrollOffset / (float) (listHeight - this.height), 0d, 1d );
        int scrolledPixels = (int) (scrolled * (insideHeight - scrollBarHeight));
        int scrollTop = 2 + y + scrolledPixels;
        boolean hovered = GuiUtil.isMouseInRegion( mouseX, mouseY, x + width - scrollBarWidth, y, scrollBarWidth, height );
        fill( 2 + x + width - scrollBarWidth, scrollTop, x + width - 2, scrollTop + scrollBarHeight, StyleManager.getCurrentStyle().getFGColor( true, hovered ).getInt() );
        int x = this.x + padding;
        int y = this.y + padding - scrollOffset;
        int maxY = this.x + this.height;

        RenderUtil.glScissorBox( this.x + 1, this.y + 1, this.x + this.width - scrollBarWidth - 1, this.y + this.height - 1 );
        GL11.glEnable( GL11.GL_SCISSOR_TEST );

        for (Widget w : widgets) {
            w.x = x;
            w.y = y;
            w.setWidth( width - padding - padding - scrollBarWidth );

            if (this.y <= w.y + w.getHeight() && w.y < maxY) {
                w.active = true;
                w.visible = true;
                w.render( mouseX, mouseY, partialTicks );
            }
            else {
                w.active = false;
                w.visible = false;
            }
            y = w.y + w.getHeight() + padding;
        }

        GL11.glDisable( GL11.GL_SCISSOR_TEST );
    }

}