/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class EventRenderGui
extends Event {
    private ScaledResolution resolution;

    public void fire(ScaledResolution resolution) {
        this.resolution = resolution;
        super.fire();
    }

    public ScaledResolution getResolution() {
        return this.resolution;
    }
}

