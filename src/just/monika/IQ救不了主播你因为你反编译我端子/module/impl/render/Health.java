/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.render;

import java.awt.Color;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderGui;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;

public class Health
extends Module {
    public Health(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventRenderGui.class})
    public void onEvent(Event event) {
        if (event instanceof EventRenderGui) {
            EventRenderGui er = (EventRenderGui)event;
            int width = er.getResolution().getScaledWidth() / 2;
            int height = er.getResolution().getScaledHeight() / 2;
            String XD = "" + (int) Health.mc.thePlayer.getHealth();
            int XDDD = Health.mc.fontRendererObj.getStringWidth(XD);
            float health = Health.mc.thePlayer.getHealth();
            if (health > 20.0f) {
                health = 20.0f;
            }
            int red = (int)Math.abs(health * 5.0f * 0.01f * 0.0f + (1.0f - health * 5.0f * 0.01f) * 255.0f);
            int green = (int)Math.abs(health * 5.0f * 0.01f * 255.0f + (1.0f - health * 5.0f * 0.01f) * 0.0f);
            Color customColor = new Color(red, green, 0).brighter();
            Health.mc.fontRendererObj.drawStringWithShadow(XD, (- XDDD) / 2 + width, height - 17, customColor.getRGB());
        }
    }
}

