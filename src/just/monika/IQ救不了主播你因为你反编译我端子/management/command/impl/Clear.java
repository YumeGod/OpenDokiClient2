/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import net.minecraft.client.Minecraft;

public class Clear
extends Command {
    public Clear(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }

    @Override
    public String getUsage() {
        return "Clear";
    }

    public void onEvent(Event event) {
    }
}

