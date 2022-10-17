/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.management.friend.FriendManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.notifications.Notifications;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import net.minecraft.network.play.server.S38PacketPlayerListItem;

public class FriendAlert
extends Module {
    private boolean connect;
    private String name;
    private int currentY;
    private int targetY;
    just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();

    public FriendAlert(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        S38PacketPlayerListItem packet;
        EventPacket ep = (EventPacket)event;
        if (ep.isIncoming() && ep.getPacket() instanceof S38PacketPlayerListItem && (packet = (S38PacketPlayerListItem)ep.getPacket()).func_179768_b() == S38PacketPlayerListItem.Action.ADD_PLAYER) {
            for (Object o : packet.func_179767_a()) {
                S38PacketPlayerListItem.AddPlayerData data = (S38PacketPlayerListItem.AddPlayerData)o;
                if (!FriendManager.isFriend(data.field_179964_d.getName())) continue;
                Notifications.getManager().post("Friend Alert", "\u00a7b" + data.field_179964_d.getName() + " has joined!", 2500, Notifications.Type.INFO);
            }
        }
    }
}

