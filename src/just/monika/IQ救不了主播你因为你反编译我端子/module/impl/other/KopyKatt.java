/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S02PacketChat;

public class KopyKatt
extends Module {
    public static EntityPlayer target;

    public KopyKatt(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        String message;
        S02PacketChat pc;
        EventPacket ep = (EventPacket)event;
        if (ep.isIncoming() && ep.getPacket() instanceof S02PacketChat && target != null && (message = (pc = (S02PacketChat)ep.getPacket()).func_148915_c().getUnformattedText().toString()).contains(target.getDisplayName().getUnformattedText().toString()) && message.contains(">")) {
            String[] yourMessage = message.split(">");
            String msg = yourMessage[1];
            msg = msg.replace(KopyKatt.mc.thePlayer.getName(), target.getName());
            msg = msg.replace("Im ", "You're ");
            msg = msg.replace("I'm ", "You're ");
            msg = msg.replace("im ", "You're ");
            msg = msg.replace("i'm ", "You're ");
            msg = msg.replace("I ", "You ");
            msg = msg.replace("i ", "You ");
            String curseCheck = (msg = msg.replace("hate", "love")).toLowerCase();
            if (curseCheck.contains("nigger") || curseCheck.contains("fucker") || curseCheck.contains("dyke") || curseCheck.contains("faggot") || curseCheck.contains("asshole") || curseCheck.contains("fgt") || curseCheck.contains("kys") || curseCheck.contains("kill yourself") || curseCheck.contains("jew") || curseCheck.contains("cunt") || curseCheck.contains("chink") || curseCheck.contains("fuck") || curseCheck.contains("gay") || curseCheck.contains("fag") || curseCheck.contains("dick")) {
                msg = "Hey! " + target.getName() + ", that's not nice.";
            }
            ChatUtil.sendChat(msg);
        }
    }
}

