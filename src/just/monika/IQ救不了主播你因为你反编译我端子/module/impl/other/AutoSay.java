/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;

public class AutoSay
extends Module {
    just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();
    public static final String WORDS = "SAY";
    public final String DELAY = "DELAY";

    public AutoSay(ModuleData data) {
        super(data);
        this.settings.put("SAY", new Setting<String>("SAY", "/warp", "Message to send."));
        this.settings.put("DELAY", new Setting<Integer>("DELAY", 500, "MS delay between messages.", 100.0, 100.0, 10000.0));
    }

    @RegisterEvent(events={EventTick.class})
    public void onEvent(Event event) {
        String message = ((String)((Setting)this.settings.get("SAY")).getValue()).toString();
        if (this.timer.delay(((Number)((Setting)this.settings.get("DELAY")).getValue()).longValue())) {
            ChatUtil.sendChat(message + " " + (int)(Math.random() * 100000.0));
            this.timer.reset();
        }
    }
}

