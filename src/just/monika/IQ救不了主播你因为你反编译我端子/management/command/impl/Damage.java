/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Damage
extends Command {
    static Minecraft mc = Minecraft.getMinecraft();

    public Damage(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        Damage.damagePlayer();
    }

    public static void damagePlayer() {
        for (int index = 0; index < 70; ++index) {
            Damage.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Damage.mc.thePlayer.posX, Damage.mc.thePlayer.posY + 0.06, Damage.mc.thePlayer.posZ, false));
            Damage.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Damage.mc.thePlayer.posX, Damage.mc.thePlayer.posY, Damage.mc.thePlayer.posZ, false));
        }
        Damage.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Damage.mc.thePlayer.posX, Damage.mc.thePlayer.posY + 0.1, Damage.mc.thePlayer.posZ, false));
    }

    @Override
    public String getUsage() {
        return null;
    }

    public void onEvent(Event event) {
    }
}

