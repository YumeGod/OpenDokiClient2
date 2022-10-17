/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.command.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.EventSystem;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.Command;
import just.monika.IQ救不了主播你因为你反编译我端子.util.NetUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.server.S3APacketTabComplete;

public class PluginFinder
extends Command {
    private Timer timer = new Timer();

    public PluginFinder(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        try {
            this.timer.reset();
            EventSystem.register(this);
            NetUtil.sendPacket(new C14PacketTabComplete("/"));
            ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Listening for a S3APacketTabComplete for 20s! This service has been brought to you by Ceanko\u2122.");
        }
        catch (Exception ceankoFucksOff) {
            ceankoFucksOff.printStackTrace();
        }
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        try {
            EventPacket ep = (EventPacket)event;
            if (ep.getPacket() instanceof S3APacketTabComplete) {
                S3APacketTabComplete packet = (S3APacketTabComplete)ep.getPacket();
                String[] commands = packet.func_149630_c();
                String message = "";
                int size = 0;
                for (String command : commands) {
                    String pluginName = command.split(":")[0].substring(1);
                    if (message.contains(pluginName) || !command.contains(":") || pluginName.equalsIgnoreCase("minecraft") || pluginName.equalsIgnoreCase("bukkit")) continue;
                    ++size;
                    message = message.isEmpty() ? message + pluginName : message + "\u00a78, \u00a7a" + pluginName;
                }
                if (!message.isEmpty()) {
                    ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 \u00a77Plugins (\u00a7f" + size + "\u00a77): \u00a7a " + message + "\u00a77.");
                } else {
                    ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Plugins: none.");
                }
                EventSystem.unregister(this);
                event.setCancelled(true);
            }
            if (this.timer.delay(20000.0f)) {
                EventSystem.unregister(this);
                ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 Stopped listening for an S3APacketTabComplete! Took to long (20s)!");
            }
        }
        catch (Exception ceankoFucksOff) {
            ceankoFucksOff.printStackTrace();
        }
    }

    @Override
    public String getUsage() {
        return "Finds plugins, I think.";
    }
}

