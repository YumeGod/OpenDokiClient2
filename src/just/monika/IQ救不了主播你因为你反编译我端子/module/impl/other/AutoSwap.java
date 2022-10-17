/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import just.monika.IQ救不了主播你因为你反编译我端子.util.misc.ChatUtil;
import org.lwjgl.input.Keyboard;

public class AutoSwap
extends Module {
    public static int multiSwap;
    public static boolean isSwapping;
    public static boolean settingKey;
    public static boolean keysSet;
    private static final String MULTI = "MULTI";
    private static final String SINGLE = "SINGLE";
    public int multiKey;
    public int single;
    just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();

    public AutoSwap(ModuleData data) {
        super(data);
    }

    @Override
    public void onEnable() {
        isSwapping = false;
    }

    @RegisterEvent(events={EventMotion.class})
    public void onEvent(Event event) {
        EventMotion em = (EventMotion)event;
        if (em.isPre()) {
            if (settingKey && this.timer.delay(1000.0f) && !keysSet) {
                ChatUtil.printChat("\u00a7c[\u00a7fS\u00a7c]\u00a77 press your key you'd like to set for Multi Swap.");
                keysSet = true;
            } else if (settingKey || !keysSet) {
                // empty if block
            }
            if (keysSet && Keyboard.getEventKey() != 0) {
                this.multiKey = Keyboard.getEventKey();
            }
        }
        if (!em.isPost() || !settingKey) {
            // empty if block
        }
    }
}

