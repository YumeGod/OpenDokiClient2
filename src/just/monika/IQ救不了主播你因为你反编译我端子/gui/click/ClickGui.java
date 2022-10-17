/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.click;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.components.MainPanel;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.Menu;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.DokiClientCgui;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ui.UI;
import just.monika.IQ救不了主播你因为你反编译我端子.management.animate.Opacity;
import just.monika.IQ救不了主播你因为你反编译我端子.util.JelloRenderUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.Colors;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

public class ClickGui
extends GuiScreen {
    private MainPanel mainPanel;
    public static Menu menu;
    private List<UI> themes = new CopyOnWriteArrayList<UI>();
    private Opacity opacity = new Opacity(0);

    public List<UI> getThemes() {
        return this.themes;
    }

    public ClickGui() {
        this.themes.add(new DokiClientCgui());
        this.mainPanel = new MainPanel("DokiClient", 50.0f, 50.0f, this.themes.get(0));
        this.themes.get(0).mainConstructor(this, this.mainPanel);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        this.opacity.interpolate(100);
        JelloRenderUtil.rectangle(0.0, 0.0, res.getScaledWidth(), res.getScaledHeight(), Colors.getColor(0, (int)this.opacity.getOpacity()));
        this.mainPanel.draw(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        this.mainPanel.mouseMovedOrUp(mouseX, mouseY, mouseButton);
        super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        try {
            this.mainPanel.mouseClicked(mouseX, mouseY, clickedButton);
            super.mouseClicked(mouseX, mouseY, clickedButton);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();
        if (Keyboard.getEventKeyState()) {
            this.mainPanel.keyPressed(Keyboard.getEventKey());
        }
    }

    @Override
    public void onGuiClosed() {
        this.opacity.setOpacity(0.0f);
        this.themes.get(0).onClose();
    }
}

