/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Rotate;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Translate;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;

public class Button {
    public Rotate rotate = new Rotate(0.0f);
    public Translate translate = new Translate(0.0f, 0.0f);
    public float x;
    public float y;
    public String name;
    public CategoryPanel panel;
    public boolean enabled;
    public Module module;
    public boolean isBinding;

    public Button(CategoryPanel panel, String name, float x, float y, Module module) {
        this.panel = panel;
        this.name = name;
        this.x = x;
        this.y = y;
        this.module = module;
        this.enabled = module.isEnabled();
        panel.categoryButton.panel.theme.buttonContructor(this, this.panel);
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            if (!this.panel.visible) continue;
            theme.buttonDraw(this, x, y, this.panel);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.buttonMouseClicked(this, x, y, button, this.panel);
        }
    }

    public void keyPressed(int key) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.buttonKeyPressed(this, key);
        }
    }
}

