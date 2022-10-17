/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import net.minecraft.network.Packet;

public class EventSendPacket
extends Event {
    private Packet packet;
    private boolean pre;

    public void fire(boolean state, Packet packet) {
        this.pre = state;
        this.packet = packet;
        super.fire();
    }

    public Packet getPacket() {
        return this.packet;
    }

    public boolean isPre() {
        return this.pre;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public void setState(boolean state) {
        this.pre = state;
    }
}

