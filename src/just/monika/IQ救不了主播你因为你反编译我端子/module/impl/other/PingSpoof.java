/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.MathUtils;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class PingSpoof
extends Module {
    private just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();
    private List<Packet> packetList = new CopyOnWriteArrayList<Packet>();
    private String WAIT = "WAIT";

    public PingSpoof(ModuleData data) {
        super(data);
        this.settings.put(this.WAIT, new Setting<Integer>(this.WAIT, 15, "Seconds to wait before sending packets again.", 1.0, 5.0, 30.0));
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        if (event instanceof EventPacket) {
            EventPacket ep = (EventPacket)event;
            if (ep.isOutgoing() && ep.getPacket() instanceof C00PacketKeepAlive && PingSpoof.mc.thePlayer.isEntityAlive()) {
                this.packetList.add(ep.getPacket());
                event.setCancelled(true);
            }
            if (this.timer.delay(1000 * ((Number)((Setting)this.settings.get(this.WAIT)).getValue()).intValue())) {
                if (!this.packetList.isEmpty()) {
                    int i = 0;
                    double totalPackets = MathUtils.getIncremental(Math.random() * 10.0, 1.0);
                    for (Packet packet : this.packetList) {
                        if ((double)i >= totalPackets) continue;
                        ++i;
                        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(packet);
                        this.packetList.remove(packet);
                    }
                }
                mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C00PacketKeepAlive(10000));
                this.timer.reset();
            }
        }
    }
}

