/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;

public class DropdownButton {
    public String name;
    public float x;
    public float y;
    public DropdownBox box;

    public DropdownButton(String name, float x, float y, DropdownBox box) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.box = box;
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.dropDownButtonDraw(this, this.box, x, y);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.dropDownButtonMouseClicked(this, this.box, x, y, button);
        }
    }
}

