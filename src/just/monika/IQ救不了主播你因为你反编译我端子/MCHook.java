/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.renderer.texture.TextureManager;

public class MCHook
extends Minecraft {
    public MCHook(GameConfiguration gc) {
        super(gc);
    }

    @Override
    protected void func_180510_a(TextureManager texMan) {
        try {
            new DokiDokiLegitClient().setup();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        super.func_180510_a(texMan);
    }
}

