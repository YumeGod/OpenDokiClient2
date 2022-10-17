/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Expand;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Translate;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;

public class Checkbox {
    public Translate translate = new Translate(0.0f, 0.0f);
    public Expand expand = new Expand(0.0f, 0.0f, 0.0f, 0.0f);
    public CategoryPanel panel;
    public Module module;
    public boolean enabled;
    public float x;
    public float y;
    public String name;
    public Setting setting;

    public Checkbox(CategoryPanel panel, String name, float x, float y, Setting setting) {
        this.panel = panel;
        this.name = name;
        this.x = x;
        this.y = y;
        this.setting = setting;
        this.enabled = (Boolean)setting.getValue();
    }

    public Checkbox(CategoryPanel panel, String name, float x, float y, Setting setting, Module module) {
        this.panel = panel;
        this.name = name;
        this.x = x;
        this.y = y;
        this.setting = setting;
        this.enabled = (Boolean)setting.getValue();
        this.module = module;
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            if (!this.panel.visible) continue;
            theme.checkBoxDraw(this, x, y, this.panel);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.checkBoxMouseClicked(this, x, y, button, this.panel);
        }
    }
}

