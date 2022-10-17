/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.util;

import net.minecraft.network.Packet;

public class NetUtil
implements MinecraftUtil {
    public static void sendPacketNoEvents(Packet packet) {
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(packet);
    }

    public static void sendPacket(Packet packet) {
        NetUtil.mc.thePlayer.sendQueue.addToSendQueue(packet);
    }
}

