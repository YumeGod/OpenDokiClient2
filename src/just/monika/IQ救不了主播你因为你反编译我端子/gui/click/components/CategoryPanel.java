/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components;

import java.util.ArrayList;

import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Expand;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;

public class CategoryPanel {
    public float x;
    public float y;
    public boolean visible;
    public CategoryButton categoryButton;
    public String headerString;
    public ArrayList<Button> buttons;
    public ArrayList<Slider> sliders;
    public ArrayList<DropdownBox> dropdownBoxes;
    public ArrayList<DropdownButton> dropdownButtons;
    public ArrayList<Label> labels;
    public ArrayList<Checkbox> checkboxes;
    public ArrayList<ColorPreview> colorPreviews;
    public ArrayList<RGBSlider> rgbSliders;
    public Module settingModule;
    public Expand expand = new Expand(0.0f, 0.0f, 0.0f, 0.0f);

    public CategoryPanel(String name, CategoryButton categoryButton, float x, float y) {
        this.headerString = name;
        this.x = x;
        this.y = y;
        this.categoryButton = categoryButton;
        this.colorPreviews = new ArrayList();
        this.buttons = new ArrayList();
        this.sliders = new ArrayList();
        this.dropdownBoxes = new ArrayList();
        this.dropdownButtons = new ArrayList();
        this.labels = new ArrayList();
        this.checkboxes = new ArrayList();
        this.rgbSliders = new ArrayList();
        this.visible = false;
        categoryButton.panel.theme.categoryPanelConstructor(this, categoryButton, x, y);
    }

    public void draw(float x, float y) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.categoryPanelDraw(this, x, y);
        }
    }

    public void mouseClicked(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.categoryPanelMouseClicked(this, x, y, button);
        }
    }

    public void mouseReleased(int x, int y, int button) {
        for (UI theme : DokiDokiLegitClient.getClickGui().getThemes()) {
            theme.categoryPanelMouseMovedOrUp(this, x, y, button);
        }
    }
}

