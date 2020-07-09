package creativeeditor.handlers;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChatHandler {
    private static String lastMessage;

    @SubscribeEvent
    public static void onChat( ClientChatReceivedEvent event ) {
        String formatted = event.getMessage().getFormattedText();
        if (formatted.equals( lastMessage ))
            event.setCanceled( true );
        else
            lastMessage = formatted;
    }
}
