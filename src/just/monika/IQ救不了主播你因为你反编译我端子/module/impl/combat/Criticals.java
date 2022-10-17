/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.combat;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Options;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S2APacketParticles;

public class Criticals
extends Module {
    private static String PACKET = "MODE";

    public Criticals(ModuleData data) {
        super(data);
        this.settings.put(PACKET, new Setting<Options>(PACKET, new Options("Mode", "Packet", new String[]{"Packet", "Jump", "MiniJump"}), "Critical attack method."));
    }

    @RegisterEvent(events={EventPacket.class, EventMotion.class})
    public void onEvent(Event event) {
        EventPacket ep;
        if (event instanceof EventMotion) {
            EventMotion em = (EventMotion)event;
            this.setSuffix(((Options)((Setting)this.settings.get(PACKET)).getValue()).getSelected());
            if (((Options)((Setting)this.settings.get(PACKET)).getValue()).getSelected().equalsIgnoreCase("MiniJump") && em.isPre() && Criticals.mc.thePlayer.isCollidedVertically && !Criticals.mc.thePlayer.isJumping) {
                if (Criticals.mc.thePlayer.ticksExisted % 2 == 0 && Criticals.mc.thePlayer.isSwingInProgress) {
                    em.setY(em.getY() + 0.062511);
                }
                if (em.getY() == Criticals.mc.thePlayer.posY && em.isOnground()) {
                    em.setGround(false);
                }
            }
        }
        if (event instanceof EventPacket && (ep = (EventPacket)event).isOutgoing() && ep.getPacket() instanceof C02PacketUseEntity && !(ep.getPacket() instanceof S2APacketParticles) && !(ep.getPacket() instanceof C0APacketAnimation)) {
            assert (ep.getPacket() instanceof C02PacketUseEntity);
            C02PacketUseEntity packet = (C02PacketUseEntity)ep.getPacket();
            if (packet.getAction() == C02PacketUseEntity.Action.ATTACK && Criticals.mc.thePlayer.isCollidedVertically && Killaura.allowCrits) {
                switch (((Options)((Setting)this.settings.get(PACKET)).getValue()).getSelected()) {
                    case "Packet": {
                        Criticals.doCrits();
                        break;
                    }
                    case "Jump": {
                        Criticals.mc.thePlayer.jump();
                    }
                }
            }
        }
    }

    static void doCrits() {
        for (double offset : new double[]{0.05, 0.0, 0.03, 0.0}) {
            Criticals.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + offset, Criticals.mc.thePlayer.posZ, false));
        }
    }
}

