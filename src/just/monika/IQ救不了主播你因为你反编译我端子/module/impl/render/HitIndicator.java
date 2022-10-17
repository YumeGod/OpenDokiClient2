/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.render;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRender3D;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.network.play.server.S06PacketUpdateHealth;

public class HitIndicator
extends Module {
    public HitIndicator(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventRender3D.class, EventPacket.class})
    public void onEvent(Event event) {
        EventPacket ep;
        if (event instanceof EventPacket && (ep = (EventPacket)event).isIncoming() && ep.getPacket() instanceof S06PacketUpdateHealth) {
            S06PacketUpdateHealth s06PacketUpdateHealth = (S06PacketUpdateHealth)ep.getPacket();
        }
        if (event instanceof EventRender3D) {
            // empty if block
        }
    }
}

