/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.lwjgl.opengl.Display
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.screen.impl.mainmenu;

import java.io.IOException;

import just.monika.IQ救不了主播你因为你反编译我端子.gui.altmanager.GuiAltManager;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.screen.component.GuiMenuButton;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.screen.component.particles.ParticleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiModdedMainMenu
extends GuiScreen {
    private ParticleManager particles = new ParticleManager();
    private ResourceLocation text = new ResourceLocation("textures/big.png");
    private ResourceLocation background = new ResourceLocation("textures/mainmenubackground.png");

    @Override
    public void initGui() {
        super.initGui();
        String strSSP = I18n.format("Singleplayer", new Object[0]);
        String strSMP = I18n.format("Multiplayer", new Object[0]);
        String strOptions = I18n.format("Options", new Object[0]);
        String strQuit = I18n.format("Exit", new Object[0]);
        String strLang = I18n.format("Language", new Object[0]);
        String strAccounts = "Accounts";
        int initHeight = this.height / 4 + 48;
        int objHeight = 17;
        int objWidth = 63;
        int xMid = this.width / 2 - 75;
        this.buttonList.add(new GuiMenuButton(0, xMid - 150, initHeight, objWidth, objHeight, strSSP));
        this.buttonList.add(new GuiMenuButton(1, xMid - 50, initHeight, objWidth, objHeight, strSMP));
        this.buttonList.add(new GuiMenuButton(2, xMid + 50, initHeight, objWidth, objHeight, strOptions));
        this.buttonList.add(new GuiMenuButton(3, xMid + 150, initHeight, objWidth, objHeight, strLang));
        this.buttonList.add(new GuiMenuButton(4, xMid - 100, initHeight + 100, objWidth, objHeight, strAccounts));
        this.buttonList.add(new GuiMenuButton(5, xMid + 100, initHeight + 100, objWidth, objHeight, strQuit));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        } else if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        } else if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        } else if (button.id == 3) {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        } else if (button.id == 4) {
            this.mc.displayGuiScreen(new GuiAltManager());
        } else if (button.id == 5) {
            this.mc.shutdown();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();
        this.mc.getTextureManager().bindTexture(this.background);
        GuiModdedMainMenu.drawScaledCustomSizeModalRect(0, 0, 0.0f, 0.0f, w + 2, h, w + 2, h, w + 2, h);
        GlStateManager.bindTexture(0);
        GlStateManager.enableBlend();
        GlStateManager.disableBlend();
        this.particles.render(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

