/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import java.util.ArrayList;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.util.EnumChatFormatting;

public class Help
extends Command {
    public Help(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        int i = 1;
        if (args == null) {
            ArrayList<String> used = new ArrayList<String>();
            for (Command command : DokiDokiLegitClient.commandManager.getCommands()) {
                if (used.contains(command.getName())) continue;
                used.add(command.getName());
                ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 " + i + ". " + command.getName() + " - " + command.getDescription());
                ++i;
            }
            ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Specify a name of a command for details about it.");
        } else if (args.length > 0) {
            String name = args[0];
            Command command = DokiDokiLegitClient.commandManager.getCommand(name);
            if (command == null) {
                ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Could not find: " + name);
                return;
            }
            ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 " + command.getName() + ": " + command.getDescription());
            ChatUtil.printChat(command.getUsage());
        }
    }

    @Override
    public String getUsage() {
        return "Help " + (Object)((Object)EnumChatFormatting.ITALIC) + " [optional] " + (Object)((Object)EnumChatFormatting.RESET) + "<Command>";
    }

    public void onEvent(Event event) {
    }
}

