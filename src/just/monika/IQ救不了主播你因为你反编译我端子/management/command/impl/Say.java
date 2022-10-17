/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;

public class Say
extends Command {
    public Say(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        if (args == null) {
            return;
        }
        if (args.length > 0) {
            StringBuilder out = new StringBuilder();
            for (String word : args) {
                out.append(word + " ");
            }
            String message = out.substring(0, out.length() - 1);
            message = message.replaceAll("&", "\u00a7");
            ChatUtil.printChat(message);
        }
    }

    @Override
    public String getUsage() {
        return "Say <Message>";
    }

    public void onEvent(Event event) {
    }
}

