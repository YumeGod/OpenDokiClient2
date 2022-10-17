/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.hud;

import java.awt.Color;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderGui;
import just.monika.IQ救不了主播你因为你反编译我端子.management.ColorManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Opacity;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other.ChatCommands;
import just.monika.IQ救不了主播你因为你反编译我端子.util.JelloRenderUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.Colors;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.TTFFontRenderer;

public class Enabled
extends Module {
    private String RAINBOW = "RAINBOW";
    private Opacity hue = new Opacity(0);

    public Enabled(ModuleData data) {
        super(data);
        this.settings.put(this.RAINBOW, new Setting<Boolean>(this.RAINBOW, false, "Rainbow array list."));
    }

    @RegisterEvent(events={EventRenderGui.class})
    public void onEvent(Event event) {
        TTFFontRenderer normal = DokiDokiLegitClient.fm.getFont("SFB 8");
        if (Enabled.mc.gameSettings.showDebugInfo) {
            return;
        }
        EventRenderGui e = (EventRenderGui)event;
        boolean rainbow = (Boolean)((Setting)this.settings.get(this.RAINBOW)).getValue();
        boolean t = DokiDokiLegitClient.getModuleManager().isEnabled(TabGUI.class);
        JelloRenderUtil.rectangle(2.0, 1.0, 56.0, 13.0, Colors.getColor(0, t ? TabGUI.opacity : 200));
        JelloRenderUtil.rectangle(2.3, 1.3, 4.0, 12.7, Colors.getColor(ColorManager.hudColor.red, ColorManager.hudColor.green, ColorManager.hudColor.blue, t ? TabGUI.opacity + 40 : 232));
        DokiDokiLegitClient.fm.getFont("SFR 11").drawStringWithShadow("DokiClient", 5.0f, 3.0f, Colors.getColor(255, t ? TabGUI.opacity + 64 : 232));
        float offset = DokiDokiLegitClient.fm.getFont("SFR 11").getWidth("DokiClient");
        DokiDokiLegitClient.fm.getFont("SFB 7").drawStringWithShadow(DokiDokiLegitClient.version, 4.0f + offset, 3.0f, rainbow ? Color.getHSBColor(this.hue.getOpacity() / 255.0f, 0.6f, 1.0f).getRGB() : Colors.getColor(232, 100, 80, t ? TabGUI.opacity + 64 : 255));
        int y = 4;
        CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
        for (Module module : DokiDokiLegitClient.getModuleManager().getArray()) {
            if (!module.isEnabled() || this.shouldHide(module)) continue;
            modules.add(module);
        }
        modules.sort(Comparator.comparingDouble(m -> - normal.getWidth(m.getSuffix() != null ? m.getName() + " " + m.getSuffix() : m.getName())));
        this.hue.interp(256, 1);
        if (this.hue.getOpacity() > 255.0f) {
            this.hue.setOpacity(0.0f);
        }
        float h = this.hue.getOpacity();
        for (Module module : modules) {
            if (h > 255.0f) {
                h = 0.0f;
            }
            Color color = Color.getHSBColor(h / 255.0f, 0.6f, 1.0f);
            int c = color.getRGB();
            String suffix = module.getSuffix() != null ? " " + module.getSuffix() : "";
            float x = (float)e.getResolution().getScaledWidth() - normal.getWidth(module.getName() + suffix) - 2.0f;
            JelloRenderUtil.rectangle(x - 1.0f, (double)y - 4.3, e.getResolution().getScaledWidth(), (double)y + 5.5, Colors.getColor(0, 160));
            JelloRenderUtil.rectangle((double)e.getResolution().getScaledWidth() - 1.6, (double)y - 4.3, e.getResolution().getScaledWidth(), (double)y + 5.5, rainbow ? c : -1);
            normal.drawStringWithShadow(module.getName(), x, y - 1, rainbow ? c : -1);
            if (!Objects.equals(suffix, "")) {
                normal.drawStringWithShadow(suffix, x + normal.getWidth(module.getName()) - 2.0f, y - 1, Colors.getColor(Colors.getColor(150)));
            }
            h += 5.0f;
            y += 10;
        }
        Enabled.mc.fontRendererObj.drawString("", 0.0f, 0.0f, -1);
    }

    private boolean shouldHide(Module module) {
        ModuleData.Type type = module.getType();
        if (this.isBlacklisted(module.getClass())) {
            return true;
        }
        return false;
    }

    private boolean isBlacklisted(Class<? extends Module> clazz) {
        if (clazz.equals(ChatCommands.class) || clazz.equals(TabGUI.class) || clazz.equals(BubbleGui.class) || clazz.equals(Enabled.class)) {
            return true;
        }
        return false;
    }
}

