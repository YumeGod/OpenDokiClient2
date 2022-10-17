/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.management.friend.Friend;
import just.monika.IQ救不了主播你因为你反编译我端子.management.friend.FriendManager;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.PathFind;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S02PacketChat;

public class CMDBot
extends Module {
    private boolean following;
    private String followName;

    public CMDBot(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventPacket.class, EventMotion.class})
    public void onEvent(Event event) {
        EventPacket ep;
        EventMotion e;
        if (event instanceof EventMotion && (e = (EventMotion)event).isPre()) {
            if (CMDBot.mc.thePlayer.isDead) {
                CMDBot.mc.thePlayer.respawnPlayer();
            }
            if (this.following) {
                try {
                    PathFind pf = new PathFind(this.followName);
                    e.setPitch(PathFind.fakePitch - 30.0f);
                    e.setYaw(PathFind.fakeYaw);
                }
                catch (Exception pf) {
                    // empty catch block
                }
            }
        }
        if (event instanceof EventPacket && (ep = (EventPacket)event).isIncoming() && ep.getPacket() instanceof S02PacketChat) {
            String s;
            EntityPlayer mod;
            S02PacketChat message = (S02PacketChat)ep.getPacket();
            if (message.func_148915_c().getFormattedText().contains("-follow")) {
                for (Friend friend : FriendManager.friendsList) {
                    if (!message.func_148915_c().getFormattedText().contains(friend.name)) continue;
                    s = message.func_148915_c().getFormattedText();
                    s = s.substring(s.indexOf("-follow ") + 8);
                    s = s.substring(0, s.indexOf("\u00a7"));
                    this.following = true;
                    this.followName = s;
                    break;
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-stopfollow")) {
                for (Friend friend : FriendManager.friendsList) {
                    if (!message.func_148915_c().getFormattedText().contains(friend.name)) continue;
                    s = message.func_148915_c().getFormattedText();
                    s = s.substring(s.indexOf("-stopfollow ") + 12);
                    s = s.substring(0, s.indexOf("\u00a7"));
                    this.following = false;
                    this.followName = "";
                    break;
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-amandatodd")) {
                for (Friend friend : FriendManager.friendsList) {
                    if (!message.func_148915_c().getFormattedText().contains(friend.name)) continue;
                    for (int index = 0; index < 81; ++index) {
                        CMDBot.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(CMDBot.mc.thePlayer.posX, CMDBot.mc.thePlayer.posY + 20.0, CMDBot.mc.thePlayer.posZ, false));
                        CMDBot.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(CMDBot.mc.thePlayer.posX, CMDBot.mc.thePlayer.posY, CMDBot.mc.thePlayer.posZ, false));
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-tpahere")) {
                for (Friend friend : FriendManager.friendsList) {
                    if (!message.func_148915_c().getFormattedText().contains(friend.name)) continue;
                    ChatUtil.sendChat("/tpa " + friend.name);
                    break;
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-friend ")) {
                block7 : for (Object o : CMDBot.mc.theWorld.getLoadedEntityList()) {
                    if (!(o instanceof EntityPlayer)) continue;
                    mod = (EntityPlayer)o;
                    if (!message.func_148915_c().getFormattedText().contains("-friend " + mod.getName())) continue;
                    for (Friend friend2 : FriendManager.friendsList) {
                        if (!message.func_148915_c().getFormattedText().contains(friend2.name)) continue;
                        if (FriendManager.isFriend(mod.getName())) {
                            ChatUtil.sendChat(mod.getName() + " is already a friend.");
                            continue block7;
                        }
                        if (FriendManager.isFriend(mod.getName())) continue block7;
                        ChatUtil.sendChat(mod.getName() + " has been friended.");
                        FriendManager.addFriend(mod.getName(), mod.getName());
                        continue block7;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-friendremove ")) {
                block9 : for (Object o : CMDBot.mc.theWorld.getLoadedEntityList()) {
                    if (!(o instanceof EntityPlayer)) continue;
                    mod = (EntityPlayer)o;
                    if (!message.func_148915_c().getFormattedText().contains("-friendremove " + mod.getName())) continue;
                    for (Friend friend2 : FriendManager.friendsList) {
                        if (!message.func_148915_c().getFormattedText().contains(friend2.name)) continue;
                        if (FriendManager.isFriend(mod.getName())) {
                            FriendManager.removeFriend(mod.getName());
                            ChatUtil.sendChat(mod.getName() + " has been removed from friends.");
                            continue block9;
                        }
                        if (FriendManager.isFriend(mod.getName())) continue block9;
                        ChatUtil.sendChat(mod.getName() + " is not friended.");
                        continue block9;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-toggle ")) {
                block11 : for (Module mod2 : DokiDokiLegitClient.getModuleManager().getArray()) {
                    if (!message.func_148915_c().getFormattedText().contains("-toggle " + ((Module) mod2).getName())) continue;
                    for (Friend friend2 : FriendManager.friendsList) {
                        if (!message.func_148915_c().getFormattedText().contains(friend2.name)) continue;
                        ((Module) mod2).toggle();
                        boolean state = ((Module) mod2).isEnabled();
                        String s2 = state ? "On" : "Off";
                        continue block11;
                    }
                }
            }
        }
    }
}

