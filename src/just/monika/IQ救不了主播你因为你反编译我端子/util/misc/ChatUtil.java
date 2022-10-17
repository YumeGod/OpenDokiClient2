/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.util.misc;

import just.monika.IQ救不了主播你因为你反编译我端子.util.MinecraftUtil;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.ChatComponentText;

public class ChatUtil
implements MinecraftUtil {
    public static void printChat(String text) {
        ChatUtil.mc.thePlayer.addChatComponentMessage(new ChatComponentText(text));
    }

    public static void sendChat_NoFilter(String text) {
        ChatUtil.mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(text));
    }

    public static void sendChat(String text) {
        ChatUtil.mc.thePlayer.sendChatMessage(text);
    }
}

