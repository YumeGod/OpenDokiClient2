/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.movement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class AntiFreeze
extends Module {
    private List<Packet> packets = new CopyOnWriteArrayList<Packet>();
    private boolean sending;
    private int delay;

    public AntiFreeze(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventTick.class, EventPacket.class})
    public void onEvent(Event event) {
        if (event instanceof EventTick) {
            ++this.delay;
            if (this.delay >= 1) {
                this.sending = true;
                this.sendPackets();
                this.delay = 0;
            }
        } else if (event instanceof EventPacket) {
            boolean input;
            EventPacket ep = (EventPacket)event;
            if (this.sending) {
                return;
            }
            if (ep.isOutgoing() && ep.getPacket() instanceof C03PacketPlayer || ep.getPacket() instanceof C08PacketPlayerBlockPlacement || ep.getPacket() instanceof C07PacketPlayerDigging || ep.getPacket() instanceof C02PacketUseEntity) {
                event.setCancelled(true);
            }
            boolean bl = input = AntiFreeze.mc.gameSettings.keyBindForward.isPressed() || AntiFreeze.mc.gameSettings.keyBindBack.isPressed() || AntiFreeze.mc.gameSettings.keyBindRight.isPressed() || AntiFreeze.mc.gameSettings.keyBindLeft.isPressed();
            if (input && ep.getPacket() instanceof C03PacketPlayer) {
                this.packets.add(ep.getPacket());
            }
            if (ep.getPacket() instanceof C02PacketUseEntity) {
                this.packets.add(ep.getPacket());
                AntiFreeze.mc.thePlayer.rotationYaw -= 180.0f;
            }
        }
    }

    public void sendPackets() {
        if (this.packets.size() > 0) {
            for (Packet packet : this.packets) {
                if (packet instanceof C02PacketUseEntity || packet instanceof C08PacketPlayerBlockPlacement || packet instanceof C07PacketPlayerDigging) {
                    AntiFreeze.mc.thePlayer.swingItem();
                }
                AntiFreeze.mc.thePlayer.sendQueue.addToSendQueue(packet);
            }
        }
        this.packets.clear();
        this.sending = false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.sending = true;
        this.sendPackets();
    }
}

