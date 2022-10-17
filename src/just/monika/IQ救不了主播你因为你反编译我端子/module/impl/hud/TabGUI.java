/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.hud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventKeyPress;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderGui;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.management.ColorManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Expand;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Translate;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Options;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.MathUtils;
import just.monika.IQ救不了主播你因为你反编译我端子.util.JelloRenderUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.StringConversions;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class TabGUI
extends Module {
    private int selectedTypeX;
    private int selectedModuleY;
    private int selectedSetY;
    private int moduleBoxY;
    private int currentSetting;
    private int settingBoxX;
    private int categoryBoxY;
    private int categoryBoxX;
    private int currentCategory;
    private int targetY;
    private int targetModY;
    private int targetSetY;
    private int moduleBoxX;
    private int targetX;
    private int targetSetX;
    private boolean inModules;
    private boolean inModSet;
    private boolean inSet;
    private Module selectedModule;
    private Timer timer = new Timer();
    public static int opacity = 45;
    private int targetOpacity = 45;
    private boolean isActive;
    private Translate selectedType = new Translate(0.0f, 14.0f);
    private Translate selectedModuleT = new Translate(0.0f, 14.0f);
    private Translate selectedSettingT = new Translate(0.0f, 14.0f);
    private Expand expand = new Expand(0.0f, 14.0f, 0.0f, 0.0f);

    public TabGUI(ModuleData data) {
        super(data);
    }

    @Override
    public void onEnable() {
        this.targetY = 12;
        this.categoryBoxY = 0;
        this.currentCategory = 0;
        this.inModules = false;
        this.inModSet = false;
        this.inSet = false;
    }

    @RegisterEvent(events={EventRenderGui.class, EventTick.class, EventKeyPress.class})
    public void onEvent(Event event) {
        if (TabGUI.mc.gameSettings.showDebugInfo) {
            return;
        }
        if (event instanceof EventKeyPress) {
            EventKeyPress ek = (EventKeyPress)event;
            if (!this.isActive && this.keyCheck(ek.getKey())) {
                this.isActive = true;
                this.targetOpacity = 200;
                this.timer.reset();
            }
            if (this.isActive && this.keyCheck(ek.getKey())) {
                this.timer.reset();
            }
            if (!this.inModules) {
                if (ek.getKey() == 208) {
                    this.targetY += 12;
                    ++this.currentCategory;
                    if (this.currentCategory > ModuleData.Type.values().length - 1) {
                        this.targetY = 14;
                        this.currentCategory = 0;
                    }
                } else if (ek.getKey() == 200) {
                    this.targetY -= 12;
                    --this.currentCategory;
                    if (this.currentCategory < 0) {
                        this.targetY = this.categoryBoxY - 11;
                        this.currentCategory = ModuleData.Type.values().length - 1;
                    }
                } else if (ek.getKey() == 205) {
                    this.inModules = true;
                    this.moduleBoxY = 0;
                    this.selectedModuleT.setY(14.0f);
                    this.targetModY = 14;
                    int longestString = 0;
                    for (Module modxd : DokiDokiLegitClient.getModuleManager().getArray()) {
                        if (modxd.getType() != ModuleData.Type.values()[this.currentCategory] || (float)longestString >= DokiDokiLegitClient.fm.getFont("SFB 8").getWidth(modxd.getName())) continue;
                        longestString = (int) DokiDokiLegitClient.fm.getFont("SFB 8").getWidth(modxd.getName());
                    }
                    this.targetX = this.categoryBoxX + 5 + longestString + 15;
                    this.moduleBoxX = this.categoryBoxX + 5;
                }
            } else if (!this.inModSet) {
                if (ek.getKey() == 203) {
                    this.targetX = this.categoryBoxX + 6;
                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(110);
                        }
                        catch (InterruptedException interruptedException) {
                            // empty catch block
                        }
                        this.inModules = false;
                    }
                    );
                    thread.start();
                }
                if (ek.getKey() == 208) {
                    this.targetModY += 12;
                    ++this.moduleBoxY;
                    if (this.moduleBoxY > this.getModules(ModuleData.Type.values()[this.currentCategory]).size() - 1) {
                        this.targetModY = 14;
                        this.moduleBoxY = 0;
                    }
                } else if (ek.getKey() == 200) {
                    this.targetModY -= 12;
                    --this.moduleBoxY;
                    if (this.moduleBoxY < 0) {
                        this.targetModY = (this.getModules(ModuleData.Type.values()[this.currentCategory]).size() - 1) * 12 + 14;
                        this.moduleBoxY = this.getModules(ModuleData.Type.values()[this.currentCategory]).size() - 1;
                    }
                } else if (ek.getKey() == 28) {
                    Module mod = this.getModules(ModuleData.Type.values()[this.currentCategory]).get(this.moduleBoxY);
                    if (!mod.getName().contains("TabGUI") && mod != DokiDokiLegitClient.getModuleManager().get(BubbleGui.class)) {
                        mod.toggle();
                    }
                } else if (ek.getKey() == 205) {
                    this.selectedModule = this.getModules(ModuleData.Type.values()[this.currentCategory]).get(this.moduleBoxY);
                    if (this.getSettings(this.selectedModule) != null) {
                        this.inModSet = true;
                        this.selectedSettingT.setY(14.0f);
                        this.targetSetY = 14;
                        this.currentSetting = 0;
                        int longestString = 0;
                        for (Setting modxd : this.getSettings(this.selectedModule)) {
                            String faggotXD;
                            String string = faggotXD = modxd.getValue() instanceof Options ? ((Options)modxd.getValue()).getSelected() + "XD" : modxd.getValue().toString();
                            if ((float)longestString >= DokiDokiLegitClient.fm.getFont("SFB 8").getWidth(modxd.getName() + " " + faggotXD)) continue;
                            longestString = (int) DokiDokiLegitClient.fm.getFont("SFB 8").getWidth(modxd.getName() + " " + faggotXD);
                        }
                        this.targetSetX = this.moduleBoxX + longestString + 16;
                        this.settingBoxX = this.moduleBoxX + 4;
                    }
                }
            } else if (!this.inSet) {
                if (ek.getKey() == 203) {
                    this.targetSetX = this.moduleBoxX + 4;
                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(110);
                        }
                        catch (InterruptedException interruptedException) {
                            // empty catch block
                        }
                        this.inModSet = false;
                        this.selectedModule = null;
                    }
                    );
                    thread.start();
                } else if (ek.getKey() == 208) {
                    this.targetSetY += 12;
                    ++this.currentSetting;
                    if (this.currentSetting > this.getSettings(this.selectedModule).size() - 1) {
                        this.currentSetting = 0;
                        this.targetSetY = 14;
                    }
                } else if (ek.getKey() == 200) {
                    this.targetSetY -= 12;
                    --this.currentSetting;
                    if (this.currentSetting < 0) {
                        this.targetSetY = (this.getSettings(this.selectedModule).size() - 1) * 12 + 14;
                        this.currentSetting = this.getSettings(this.selectedModule).size() - 1;
                    }
                } else if (ek.getKey() == 205) {
                    this.inSet = true;
                }
            } else if (this.inSet) {
                if (ek.getKey() == 203) {
                    this.inSet = !this.inSet;
                } else if (ek.getKey() == 200) {
                    Setting set = this.getSettings(this.selectedModule).get(this.currentSetting);
                    if (set.getValue() instanceof Number) {
                        String str;
                        double increment = set.getInc();
                        String string = str = MathUtils.isInteger(MathUtils.getIncremental(((Number)set.getValue()).doubleValue() + increment, increment)) ? ("" + MathUtils.getIncremental(((Number)set.getValue()).doubleValue() + increment, increment) + "").replace(".0", "") : "" + MathUtils.getIncremental(((Number)set.getValue()).doubleValue() + increment, increment) + "";
                        if (Double.parseDouble(str) > set.getMax() && set.getInc() != 0.0) {
                            return;
                        }
                        Object newValue = StringConversions.castNumber(str, increment);
                        if (newValue != null) {
                            set.setValue(newValue);
                            Module.saveSettings();
                            return;
                        }
                    } else if (set.getValue().getClass().equals(Boolean.class)) {
                        boolean xd = (Boolean)set.getValue();
                        set.setValue(!xd);
                        Module.saveSettings();
                    } else if (set.getValue() instanceof Options) {
                        CopyOnWriteArrayList options = new CopyOnWriteArrayList();
                        Collections.addAll(options, ((Options)set.getValue()).getOptions());
                        for (int i = 0; i <= options.size() - 1; ++i) {
                            if (!((String)options.get(i)).equalsIgnoreCase(((Options)set.getValue()).getSelected())) continue;
                            if (i + 1 > options.size() - 1) {
                                ((Options)set.getValue()).setSelected((String)options.get(0));
                            } else {
                                ((Options)set.getValue()).setSelected((String)options.get(i + 1));
                            }
                            break;
                        }
                    }
                } else if (ek.getKey() == 208) {
                    Setting set = this.getSettings(this.selectedModule).get(this.currentSetting);
                    if (set.getValue() instanceof Number) {
                        String str;
                        double increment = set.getInc();
                        String string = str = MathUtils.isInteger(MathUtils.getIncremental(((Number)set.getValue()).doubleValue() - increment, increment)) ? ("" + MathUtils.getIncremental(((Number)set.getValue()).doubleValue() - increment, increment) + "").replace(".0", "") : "" + MathUtils.getIncremental(((Number)set.getValue()).doubleValue() - increment, increment) + "";
                        if (Double.parseDouble(str) < set.getMin() && increment != 0.0) {
                            return;
                        }
                        Object newValue = StringConversions.castNumber(str, increment);
                        if (newValue != null) {
                            set.setValue(newValue);
                            Module.saveSettings();
                            return;
                        }
                    } else if (set.getValue().getClass().equals(Boolean.class)) {
                        boolean xd = (Boolean)set.getValue();
                        set.setValue(!xd);
                        Module.saveSettings();
                    } else if (set.getValue() instanceof Options) {
                        CopyOnWriteArrayList options = new CopyOnWriteArrayList();
                        Collections.addAll(options, ((Options)set.getValue()).getOptions());
                        for (int i = options.size() - 1; i >= 0; --i) {
                            if (!((String)options.get(i)).equalsIgnoreCase(((Options)set.getValue()).getSelected())) continue;
                            if (i - 1 < 0) {
                                ((Options)set.getValue()).setSelected((String)options.get(options.size() - 1));
                            } else {
                                ((Options)set.getValue()).setSelected((String)options.get(i - 1));
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (event instanceof EventTick && this.categoryBoxY == 0) {
            int y = 13;
            int largestString = -1;
            for (ModuleData.Type type : ModuleData.Type.values()) {
                y += 12;
                if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(type.name()) <= largestString) continue;
                largestString = Minecraft.getMinecraft().fontRendererObj.getStringWidth(type.name());
            }
            this.categoryBoxY = y;
            this.categoryBoxX = largestString + 7;
            this.selectedTypeX = 2;
            this.targetY = 14;
        }
        if (event instanceof EventRenderGui) {
            EventRenderGui er = (EventRenderGui)event;
            if (this.timer.delay(4500.0f)) {
                this.targetOpacity = 100;
                this.isActive = false;
            }
            int diff3 = this.targetX - this.moduleBoxX;
            int diff5 = this.targetSetX - this.settingBoxX;
            int opacityDiff = this.targetOpacity - opacity;
            opacity = (int)((double)opacity + (double)opacityDiff * 0.1);
            this.selectedType.interpolate(this.selectedTypeX, this.targetY, Math.abs(this.selectedType.getY() - (float)this.targetY) > 12.0f ? 12 : 4);
            this.selectedModuleT.interpolate(this.categoryBoxX + 3, this.targetModY, Math.abs(this.selectedModuleT.getY() - (float)this.targetModY) > 12.0f ? 12 : 4);
            this.selectedSettingT.interpolate(0.0f, this.targetSetY, Math.abs(this.selectedSettingT.getY() - (float)this.targetSetY) > 12.0f ? 12 : 4);
            this.moduleBoxX = (int)((double)this.moduleBoxX + MathUtils.roundToPlace((double)diff3 * 0.25, 0));
            if (diff3 == 1) {
                ++this.moduleBoxX;
            } else if (diff3 == -1) {
                --this.moduleBoxX;
            }
            this.settingBoxX = (int)((double)this.settingBoxX + MathUtils.roundToPlace((double)diff5 * 0.25, 0));
            if (diff5 == 1) {
                ++this.settingBoxX;
            } else if (diff5 == -1) {
                --this.settingBoxX;
            }
            JelloRenderUtil.rectangle(2.0, 14.0, this.categoryBoxX + 2, this.categoryBoxY + 1, Colors.getColor(0, opacity));
            JelloRenderUtil.rectangle((double)this.selectedTypeX + 0.3, (double)this.selectedType.getY() + 0.3, (double)this.categoryBoxX + 1.5, (double)this.selectedType.getY() + 11.5, Colors.getColor(ColorManager.hudColor.red, ColorManager.hudColor.green, ColorManager.hudColor.blue, opacity + 64));
            int y = 18;
            for (ModuleData.Type type : ModuleData.Type.values()) {
                boolean isSelected = Math.abs((float)y - this.selectedType.getY()) < 6.0f || (float)y - this.selectedType.getY() == 6.0f;
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                DokiDokiLegitClient.fm.getFont("SFB 8").drawStringWithShadow(type.name(), isSelected ? 5.0f : 3.0f, y, Colors.getColor(255, 255, 255, opacity + 64));
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                y += 12;
            }
            if (this.inModules) {
                List<Module> xd = this.getModules(ModuleData.Type.values()[this.currentCategory]);
                y = 18;
                JelloRenderUtil.rectangle(this.categoryBoxX + 6, 14.0, this.moduleBoxX, xd.size() * 12 + 14, Colors.getColor(0, opacity));
                if (diff3 == 0 && this.moduleBoxX > this.categoryBoxX + 6) {
                    JelloRenderUtil.rectangle((double)this.categoryBoxX + 6.3, (double)this.selectedModuleT.getY() + 0.3, (double)this.moduleBoxX - 0.3, (double)this.selectedModuleT.getY() + 11.6, Colors.getColor(ColorManager.hudColor.red, ColorManager.hudColor.green, ColorManager.hudColor.blue, opacity + 64));
                    for (Module mod : this.getModules(ModuleData.Type.values()[this.currentCategory])) {
                        if (this.getSettings(mod) != null && this.selectedModule != mod) {
                            JelloRenderUtil.rectangle(this.moduleBoxX - 2, (double)y - 3.5, (double)this.moduleBoxX - 0.3, (double)y + 7.6, Colors.getColor(255, opacity + 64));
                        }
                        boolean isSelected = Math.abs((float)y - this.selectedModuleT.getY()) < 6.0f || (float)y - this.selectedModuleT.getY() == 6.0f;
                        GlStateManager.pushMatrix();
                        GlStateManager.enableAlpha();
                        DokiDokiLegitClient.fm.getFont("SFB 8").drawStringWithShadow(mod.getName(), this.categoryBoxX + (isSelected ? 9 : 7), y, mod.isEnabled() ? Colors.getColor(255, opacity + 64) : Colors.getColor(175, opacity + 64));
                        GlStateManager.disableAlpha();
                        GlStateManager.popMatrix();
                        y += 12;
                    }
                }
            }
            if (this.inModSet) {
                JelloRenderUtil.rectangle(this.moduleBoxX + 4, 14.0, this.settingBoxX, 14 + this.getSettings(this.selectedModule).size() * 12, Colors.getColor(0, opacity));
                if (this.inSet) {
                    JelloRenderUtil.rectangleBordered(this.settingBoxX + 1, this.selectedSettingT.getY(), this.settingBoxX + 4, this.selectedSettingT.getY() + 12.0f, 0.5, Colors.getColor(ColorManager.hudColor.red, ColorManager.hudColor.green, ColorManager.hudColor.blue, opacity + 64), Colors.getColor(0, opacity));
                }
                int y1 = 18;
                try {
                    JelloRenderUtil.rectangle(0.0, 0.0, 0.0, 0.0, 0);
                    JelloRenderUtil.rectangle((double)this.moduleBoxX + 4.3, (double)this.selectedSettingT.getY() + 0.3, (double)this.settingBoxX - 0.3, (double)this.selectedSettingT.getY() + 11.3, Colors.getColor(ColorManager.hudColor.red, ColorManager.hudColor.green, ColorManager.hudColor.blue, opacity + 64));
                    for (Setting setting : this.getSettings(this.selectedModule)) {
                        if (setting == null || diff5 != 0 || this.settingBoxX <= this.moduleBoxX + 4) continue;
                        boolean isSelected = Math.abs(y1 - this.selectedSettingT.getY()) < 6.0f || y1 - this.selectedSettingT.getY() == 6.0f;
                        String xd = "" + setting.getName().charAt(0) + setting.getName().toLowerCase().substring(1);
                        GlStateManager.pushMatrix();
                        String fagniger = setting.getValue() instanceof Options ? ((Options)setting.getValue()).getSelected() : setting.getValue().toString();
                        DokiDokiLegitClient.fm.getFont("SFB 8").drawStringWithShadow(xd + " " + fagniger, this.moduleBoxX + (isSelected ? 7 : 5), (float)y1, Colors.getColor(255, opacity + 64));
                        GlStateManager.popMatrix();
                        y1 += 12;
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    private boolean keyCheck(int key) {
        boolean active = false;
        switch (key) {
            case 208: {
                active = true;
                break;
            }
            case 200: {
                active = true;
                break;
            }
            case 28: {
                active = true;
                break;
            }
            case 203: {
                active = true;
                break;
            }
            case 205: {
                active = true;
                break;
            }
        }
        return active;
    }

    private List<Setting> getSettings(Module mod) {
        CopyOnWriteArrayList<Setting> settings = new CopyOnWriteArrayList<Setting>();
        settings.addAll(mod.getSettings().values());
        for (Setting setting : settings) {
            if (!setting.getValue().getClass().equals(String.class)) continue;
            settings.remove(setting);
        }
        if (settings.isEmpty()) {
            return null;
        }
        settings.sort(Comparator.comparing(Setting::getName));
        return settings;
    }

    private List<Module> getModules(ModuleData.Type type) {
        ArrayList<Module> modulesInType = new ArrayList<Module>();
        for (Module mod : DokiDokiLegitClient.getModuleManager().getArray()) {
            if (mod.getType() != type) continue;
            modulesInType.add(mod);
        }
        if (modulesInType.isEmpty()) {
            return null;
        }
        modulesInType.sort(Comparator.comparing(Module::getName));
        return modulesInType;
    }
}

