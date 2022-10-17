/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.management.friend.Friend;
import just.monika.IQ救不了主播你因为你反编译我端子.management.friend.FriendManager;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.network.play.server.S02PacketChat;

public class AutoTPA
extends Module {
    public AutoTPA(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        EventPacket ep = (EventPacket)event;
        if (ep.isIncoming() && ep.getPacket() instanceof S02PacketChat) {
            S02PacketChat s02PacketChat = (S02PacketChat)ep.getPacket();
            if (s02PacketChat.func_148915_c().getFormattedText().contains("has requested to teleport to you") || s02PacketChat.func_148915_c().getFormattedText().contains("has requested that you teleport to them")) {
                for (Friend friend : FriendManager.friendsList) {
                    if (!s02PacketChat.func_148915_c().getFormattedText().contains(friend.name) && !s02PacketChat.func_148915_c().getFormattedText().contains(friend.alias)) continue;
                    ChatUtil.sendChat_NoFilter("/tpaccept");
                    break;
                }
            }
            if (s02PacketChat.func_148915_c().getFormattedText().contains("has invited you to join their party!")) {
                for (Friend friend : FriendManager.friendsList) {
                    if (!s02PacketChat.func_148915_c().getFormattedText().contains(friend.name) && !s02PacketChat.func_148915_c().getFormattedText().contains(friend.alias)) continue;
                    ChatUtil.sendChat_NoFilter("/party accept " + friend.name);
                    break;
                }
            }
        }
    }
}

