/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;

public class SLButton {
    public float x;
    public float y;
    public String name;
    public MainPanel panel;
    public boolean load;

    public SLButton(MainPanel panel, String name, float x, float y, boolean load) {
        this.panel = panel;
        this.name = name;
        this.x = x;
        this.y = y;
        this.load = load;
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.slButtonDraw(this, x, y, this.panel);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.slButtonMouseClicked(this, x, y, button, this.panel);
        }
    }
}

