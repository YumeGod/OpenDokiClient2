/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;

public class Save
extends Command {
    public Save(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        Module.saveStatus();
        Module.saveSettings();
        ChatUtil.printChat("Saved");
    }

    @Override
    public String getUsage() {
        return null;
    }

    public void onEvent(Event event) {
    }
}

