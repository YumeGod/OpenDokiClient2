/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.StringConversions;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class ClaimFinder
extends Command {
    public ClaimFinder(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        if (args == null) {
            return;
        }
        assert (args.length == 1 && StringConversions.isNumeric(args[0]));
        if (this.mc.thePlayer.isRiding() && this.mc.thePlayer.ridingEntity instanceof EntityBoat) {
            int i;
            int chunks = Integer.parseInt(args[0]) * 8;
            double topLeftX = this.mc.thePlayer.posX - (double)chunks;
            double topLeftZ = this.mc.thePlayer.posY - (double)chunks;
            for (i = 0; i < Integer.parseInt(args[0]); ++i) {
                int chunk = 16 + i * 16;
                this.mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(topLeftX + (double)chunk, this.mc.thePlayer.posY, topLeftZ + (double)chunk, false));
                this.mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C03PacketPlayer.C04PacketPlayerPosition(topLeftX + (double)chunk, this.mc.thePlayer.posY, topLeftZ + (double)chunk, true));
            }
            for (i = 0; i < 17; ++i) {
                this.mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C02PacketUseEntity(this.mc.thePlayer.ridingEntity, C02PacketUseEntity.Action.ATTACK));
            }
            return;
        }
        ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 ERROR: you are not on a boat.");
    }

    @Override
    public String getUsage() {
        return "Invalid Chunk number!";
    }

    public void onEvent(Event event) {
    }
}

