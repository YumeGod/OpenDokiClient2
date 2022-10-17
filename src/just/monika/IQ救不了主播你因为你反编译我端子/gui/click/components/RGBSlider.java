/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;

public class RGBSlider {
    public float x;
    public float y;
    public boolean dragging;
    public double dragX;
    public ColorPreview colorPreview;
    public double lastDragX;
    public Colors rgba;

    public RGBSlider(float x, float y, ColorPreview colorPreview, Colors colors) {
        this.x = x;
        this.y = y;
        this.colorPreview = colorPreview;
        int colorShit = 0;
        switch (colors) {
            case RED: {
                colorShit = colorPreview.colorObject.getRed();
                break;
            }
            case GREEN: {
                colorShit = colorPreview.colorObject.getGreen();
                break;
            }
            case BLUE: {
                colorShit = colorPreview.colorObject.getBlue();
                break;
            }
            case ALPHA: {
                colorShit = colorPreview.colorObject.getAlpha();
            }
        }
        this.rgba = colors;
        this.dragX = colorShit * 90 / 255;
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.rgbSliderDraw(this, x, y);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.rgbSliderClick(this, x, y, button);
        }
    }

    public void mouseReleased(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.rgbSliderMovedOrUp(this, x, y, button);
        }
    }

    public static enum Colors {
        RED,
        GREEN,
        BLUE,
        ALPHA;
        

        private Colors() {
        }
    }

}

