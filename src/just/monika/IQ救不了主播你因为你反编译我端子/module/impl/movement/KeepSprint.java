/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.movement;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class KeepSprint
extends Module {
    public KeepSprint(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        C0BPacketEntityAction packet;
        EventPacket e = (EventPacket)event;
        if (e.isIncoming() && e.getPacket() instanceof C0BPacketEntityAction && (packet = (C0BPacketEntityAction)e.getPacket()).func_180764_b() == C0BPacketEntityAction.Action.STOP_SPRINTING) {
            e.setCancelled(true);
        }
    }
}

