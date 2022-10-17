/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import java.util.ArrayList;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Options;

public class DropdownBox {
    public Options option;
    public float x;
    public float y;
    public ArrayList<DropdownButton> buttons = new ArrayList();
    public CategoryPanel panel;
    public boolean active;

    public DropdownBox(Options option, float x, float y, CategoryPanel panel) {
        this.option = option;
        this.panel = panel;
        this.x = x;
        this.y = y;
        panel.categoryButton.panel.theme.dropDownContructor(this, x, y, this.panel);
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            if (!this.panel.visible) continue;
            theme.dropDownDraw(this, x, y, this.panel);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.dropDownMouseClicked(this, x, y, button, this.panel);
        }
    }
}

