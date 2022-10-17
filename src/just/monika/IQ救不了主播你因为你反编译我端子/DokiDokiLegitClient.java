package just.monika.IQ救不了主播你因为你反编译我端子;

import java.io.File;

import just.monika.IQ救不了主播你因为你反编译我端子.gui.altmanager.FileManager;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.click.ClickGui;
import just.monika.IQ救不了主播你因为你反编译我端子.gui.screen.impl.mainmenu.ClientMainMenu;
import just.monika.IQ救不了主播你因为你反编译我端子.management.ColorManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.FontManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.command.CommandManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.friend.FriendManager;
import just.monika.IQ救不了主播你因为你反编译我端子.management.waypoints.WaypointManager;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.ModuleManager;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.NharFont;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class DokiDokiLegitClient { // Fuck You Czf233
    public static DokiDokiLegitClient instance;
    public static final String author = "NotMonika";
    public static final String version = "2.0";
    private static final int VERSION_CHECK = 34646;
    public static final String clientName = "Doki";
    public static ColorManager cm;
    private final ModuleManager moduleManager;
    private static FileManager fileManager;
    private static ClickGui clickGui;
    public static CommandManager commandManager;
    private File dataDirectory;
    private GuiScreen mainMenu = new ClientMainMenu();
    private boolean isHidden;
    public static NharFont f;
    public static NharFont fs;
    public static NharFont fss;
    public static NharFont header;
    public static NharFont subHeader;
    public static FontManager fm;
    public static WaypointManager wm;
    public static TTFFontRenderer testFont;
    public static boolean outdated;

    public static FileManager getFileManager() {
        return fileManager;
    }

    public DokiDokiLegitClient() {
        commandManager = new CommandManager();
        instance = this;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        this.moduleManager = new ModuleManager(Module.class);
        FriendManager.start();
    }

    public static ClickGui getClickGui() {
        return clickGui;
    }

    public void setup() {
        commandManager.setup();
        this.dataDirectory = new File("Doki");
        this.moduleManager.setup();
        fileManager = new FileManager();
        fileManager.loadFiles();
        clickGui = new ClickGui();
        f = new NharFont("SF UI Display Bold", 10.0f);
        fs = new NharFont("SF UI Display Thin", 10.0f);
        fss = new NharFont("SF UI Display Regular", 12.0f);
        header = new NharFont("SF UI Display Semibold", 10.0f);
        subHeader = new NharFont("SF UI Display Light", 10.0f);
    }

    public static ModuleManager<Module> getModuleManager() {
        return DokiDokiLegitClient.instance.moduleManager;
    }

    public static File getDataDir() {
        return DokiDokiLegitClient.instance.dataDirectory;
    }

    public static boolean isHidden() {
        return DokiDokiLegitClient.instance.isHidden;
    }

    public static void setHidden(boolean hidden) {
        DokiDokiLegitClient.instance.isHidden = hidden;
        DokiDokiLegitClient.instance.mainMenu = hidden ? new GuiMainMenu() : new ClientMainMenu();
    }

    public int getVERSION_CHECK() {
        return 6;
    }

    static {
        cm = new ColorManager();
        fm = new FontManager();
        wm = new WaypointManager();
    }
}

