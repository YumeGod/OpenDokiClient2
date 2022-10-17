/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import net.minecraft.network.play.server.S3APacketTabComplete;

public class PluginScanner
extends Module {
    boolean isListening;
    just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();

    public PluginScanner(ModuleData data) {
        super(data);
    }

    @Override
    public void onEnable() {
        this.isListening = true;
        this.timer.reset();
    }

    @Override
    public void onDisable() {
        this.isListening = false;
        this.timer.reset();
    }

    @RegisterEvent(events={EventPacket.class})
    public void onEvent(Event event) {
        EventPacket ep = (EventPacket)event;
        if (ep.isIncoming() && ep.getPacket() instanceof S3APacketTabComplete && this.isListening && this.timer.delay(20000.0f)) {
            S3APacketTabComplete packet = (S3APacketTabComplete)ep.getPacket();
            String[] pluginsFound = packet.func_149630_c();
            ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 \u00a73Found \u00a77[\u00a73" + pluginsFound.length + "\u00a77] \u00a73plugin(s): \u00a78" + pluginsFound + "\u00a73.");
            this.toggle();
        }
    }
}

