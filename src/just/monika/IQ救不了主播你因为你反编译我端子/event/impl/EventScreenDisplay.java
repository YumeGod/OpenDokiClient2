/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import net.minecraft.client.gui.GuiScreen;

public class EventScreenDisplay
extends Event {
    private GuiScreen screen;

    public void fire(GuiScreen screen) {
        this.screen = screen;
        super.fire();
    }

    public GuiScreen getGuiScreen() {
        return this.screen;
    }
}

