/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import java.util.Arrays;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventChat;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.CommandManager;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;

public class ChatCommands
extends Module {
    private static final String KEY_PREFIX = "CHAT-PREFIX";

    public ChatCommands(ModuleData data) {
        super(data);
        this.settings.put("CHAT-PREFIX", new Setting<String>("CHAT-PREFIX", ".", "Command prefix."));
    }

    @RegisterEvent(events={EventChat.class})
    public void onEvent(Event event) {
        EventChat ec = (EventChat)event;
        String prefix = (String)((Setting)this.settings.get("CHAT-PREFIX")).getValue();
        if (!ec.getText().startsWith(prefix)) {
            return;
        }
        event.setCancelled(true);
        String[] commandBits = ec.getText().substring(prefix.length()).split(" ");
        String commandName = commandBits[0];
        Command command = CommandManager.commandMap.get(commandName.toLowerCase());
        if (command == null) {
            ChatUtil.printChat(Command.chatPrefix + commandName + "\u00a77\"\u00a78 is not a valid command.");
            return;
        }
        if (commandBits.length > 1) {
            String[] commandArguments = Arrays.copyOfRange(commandBits, 1, commandBits.length);
            command.fire(commandArguments);
        } else {
            command.fire(null);
        }
    }
}

