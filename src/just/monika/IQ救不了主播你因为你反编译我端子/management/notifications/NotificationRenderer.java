/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management.notifications;

import java.util.List;
import just.monika.IQ救不了主播你因为你反编译我端子.DokiDokiLegitClient;
import just.monika.IQ救不了主播你因为你反编译我端子.util.JelloRenderUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class NotificationRenderer
implements INotificationRenderer {
    @Override
    public void draw(List<INotification> notifications) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        float y = (float)scaledRes.getScaledHeight() - (float)(notifications.size() * 22);
        for (INotification notification : notifications) {
            Notification not = (Notification)notification;
            not.translate.interpolate(not.getTarX(), y, 12, 8);
            float subHeaderWidth = DokiDokiLegitClient.fm.getFont("SFM 8").getWidth(not.getSubtext());
            float headerWidth = DokiDokiLegitClient.fm.getFont("SFB 11").getWidth(not.getHeader());
            float x = (float)(scaledRes.getScaledWidth() - 20) - (headerWidth > subHeaderWidth ? headerWidth : subHeaderWidth);
            GL11.glPushMatrix();
            GL11.glEnable((int)3089);
            GL11.glScissor((int)((int)not.translate.getX() * 2), (int)((int)((float)scaledRes.getScaledWidth() - not.translate.getY() * 2.0f)), (int)(scaledRes.getScaledWidth() * 2), (int)((int)(not.translate.getY() + 50.0f)));
            JelloRenderUtil.rectangle(x, not.translate.getY(), scaledRes.getScaledWidth(), not.translate.getY() + 22.0f - 2.0f, Colors.getColor(0, 200));
            for (int i = 0; i < 11; ++i) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(x + 10.0f, not.translate.getY() + 13.0f, 0.0f);
                GlStateManager.rotate(270.0f, 0.0f, 0.0f, 90.0f);
                JelloRenderUtil.drawCircle(0.0f, 0.0f, 11 - i, 3, this.getColor(not.getType()));
                GlStateManager.popMatrix();
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 10.0f, not.translate.getY() + 13.0f, 0.0f);
            GlStateManager.rotate(270.0f, 0.0f, 0.0f, 90.0f);
            JelloRenderUtil.drawCircle(0.0f, 0.0f, 11.0f, 3, Colors.getColor(0));
            GlStateManager.popMatrix();
            JelloRenderUtil.rectangle((double)x + 9.6, not.translate.getY() + 5.0f, (double)x + 10.3, not.translate.getY() + 13.0f, Colors.getColor(0));
            JelloRenderUtil.rectangle((double)x + 9.6, not.translate.getY() + 15.0f, (double)x + 10.3, not.translate.getY() + 17.0f, Colors.getColor(0));
            DokiDokiLegitClient.fm.getFont("SFB 11").drawStringWithShadow(not.getHeader(), x + 18.0f, not.translate.getY() + 3.0f, -1);
            DokiDokiLegitClient.fm.getFont("SFM 8").drawStringWithShadow(not.getSubtext(), x + 20.0f, not.translate.getY() + 13.0f, -1);
            GL11.glDisable((int)3089);
            GL11.glPopMatrix();
            if (not.checkTime() >= not.getDisplayTime() + not.getStart()) {
                not.setTarX(scaledRes.getScaledWidth() + 500);
                if (not.translate.getX() >= (float)scaledRes.getScaledWidth()) {
                    notifications.remove(notification);
                }
            }
            y += 22.0f;
        }
    }

    private int getColor(Notifications.Type type) {
        int color = 0;
        switch (type) {
            case INFO: {
                color = Colors.getColor(64, 131, 214);
                break;
            }
            case NOTIFY: {
                color = Colors.getColor(242, 206, 87);
                break;
            }
            case WARNING: {
                color = Colors.getColor(226, 74, 74);
                break;
            }
        }
        return color;
    }

}

