/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ColorManager {
    private static List<ColorObject> colorObjectList = new CopyOnWriteArrayList<ColorObject>();
    public static ColorObject hudColor = new ColorObject(0, 192, 255, 255);
    public static ColorObject chestColor = new ColorObject(190, 150, 30, 255);

    public static List<ColorObject> getColorObjectList() {
        return colorObjectList;
    }

    public ColorObject getHudColor() {
        return hudColor;
    }

    public ColorManager() {
        colorObjectList.add(hudColor);
    }
}

