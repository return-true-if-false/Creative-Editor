package creativeeditor.events;

import creativeeditor.screen.widgets.ColorHelperWidget;
import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.screen.EditBookScreen;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.client.gui.screen.EditWorldScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ScreenHandler {

    @SubscribeEvent()
    public void onInitGui(GuiScreenEvent.InitGuiEvent e) {
        if (e.getGui() instanceof EditSignScreen || e.getGui() instanceof EditWorldScreen || e.getGui() instanceof EditBookScreen || e.getGui() instanceof AddServerScreen) {
            e.addWidget(new ColorHelperWidget(null, 156, 30, e.getGui().width, e.getGui().height));
        }
    }

}
