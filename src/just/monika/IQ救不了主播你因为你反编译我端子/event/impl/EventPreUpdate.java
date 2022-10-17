/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;

public class EventPreUpdate
extends Event {
    private float yaw;
    private float pitch;
    private double y;
    private boolean onground;

    public void fire(double y, float yaw, float pitch, boolean ground) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        this.onground = ground;
        super.fire();
    }
}

