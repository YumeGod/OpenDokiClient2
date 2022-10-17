/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package just.monika.IQ救不了主播你因为你反编译我端子.gui.screen.impl.mainmenu;

import java.io.File;
import java.io.IOException;
import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.screen.PanoramaScreen;
import just.monika.IQ救不了主播你因为你反编译我端子.management.SubFolder;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

public class ClientMainMenu
extends PanoramaScreen {
    private static int key = 41;
    private static final GuiVanillaMainMenu menuVanilla = new GuiVanillaMainMenu();
    private static final GuiModdedMainMenu menuModded = new GuiModdedMainMenu();

    @Override
    public void initGui() {
        this.load();
        if (this.getClass().equals(ClientMainMenu.class)) {
            this.display();
        }
    }

    private void load() {
        String file = "";
        try {
            file = FileUtils.readFileToString((File)this.getFile());
        }
        catch (IOException e) {
            return;
        }
        for (String line : file.split("\n")) {
            String[] split;
            if (!line.contains("key") || (split = line.split(":")).length <= 1) continue;
            try {
                key = Integer.parseInt(split[1]);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == key) {
            this.toggleVanilla();
            this.display();
        }
    }

    private void display() {
        if (DokiDokiLegitClient.isHidden()) {
            Minecraft.getMinecraft().displayGuiScreen(menuVanilla);
        } else {
            Minecraft.getMinecraft().displayGuiScreen(new GuiModdedMainMenu());
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void toggleVanilla() {
        DokiDokiLegitClient.setHidden(!DokiDokiLegitClient.isHidden());
        this.save();
    }

    public void save() {
        try {
            FileUtils.write((File)this.getFile(), (CharSequence)("Swap key (Toggles menus):" + key));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        File file = new File(this.getFolder().getAbsolutePath() + File.separator + "MainMenu.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public File getFolder() {
        File folder = new File(DokiDokiLegitClient.getDataDir().getAbsolutePath() + File.separator + SubFolder.Other.getFolderName());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }
}

