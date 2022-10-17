/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.render;

import java.awt.Color;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRender3D;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderEntity;
import just.monika.IQ救不了主播你因为你反编译我端子.util.JelloRenderUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.Colors;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.MCStencil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.render.Stencil;
import org.lwjgl.opengl.GL11;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Outline
extends Module {
    private String TEAM = "TEAM";
    private boolean draw = false;
    private int draws = 0;

    public Outline(ModuleData data) {
        super(data);
        this.settings.put(this.TEAM, new Setting<Boolean>(this.TEAM, false, "Team colors."));
    }

    @RegisterEvent(events={EventMotion.class, EventRender3D.class, EventRenderEntity.class})
    public void onEvent(Event event) {
        EventRenderEntity err;
        if (event instanceof EventRender3D) {
            if (!this.draw || this.draws < 10) {
                return;
            }
            for (Object obj : Outline.mc.theWorld.loadedEntityList) {
                Entity entity = (Entity)obj;
                if (entity == Outline.mc.thePlayer || !(entity instanceof EntityPlayer)) continue;
                GL11.glPushMatrix();
                double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)Outline.mc.timer.renderPartialTicks;
                double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)Outline.mc.timer.renderPartialTicks;
                double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)Outline.mc.timer.renderPartialTicks;
                GL11.glTranslated((double)(posX - RenderManager.renderPosX), (double)(posY - Math.pow(10.0, 5.0) - RenderManager.renderPosY), (double)(posZ - RenderManager.renderPosZ));
                Render entityRender = mc.getRenderManager().getEntityRenderObject(entity);
                if (entityRender != null) {
                    entityRender.doRender(entity, 0.0, 0.0, 0.0, Outline.mc.timer.renderPartialTicks, Outline.mc.timer.renderPartialTicks);
                }
                GL11.glPopMatrix();
            }
            MCStencil.checkSetupFBO();
            int list = GL11.glGenLists((int)1);
            Stencil.getInstance().startLayer();
            GL11.glPushMatrix();
            Outline.mc.entityRenderer.setupCameraTransform(Outline.mc.timer.renderPartialTicks, 0);
            GL11.glDisable((int)2929);
            Stencil.getInstance().setBuffer(true);
            GL11.glNewList((int)list, (int)4864);
            GlStateManager.enableLighting();
            for (Object obj : Outline.mc.theWorld.loadedEntityList) {
                Entity entity = (Entity)obj;
                if (entity == Outline.mc.thePlayer || !(entity instanceof EntityPlayer)) continue;
                float health = ((EntityPlayer)entity).getHealth();
                if (health > 20.0f) {
                    health = 20.0f;
                }
                float[] fractions = new float[]{0.0f, 0.5f, 1.0f};
                Color[] colors = new Color[]{Color.RED, Color.YELLOW, Color.GREEN};
                float progress = health * 5.0f * 0.01f;
                Color customColor = ESP2D.blendColors(fractions, colors, progress).brighter();
                if (((Boolean)((Setting)this.settings.get(this.TEAM)).getValue()).booleanValue() && !((EntityPlayer)entity).isMurderer) {
                    String text = entity.getDisplayName().getFormattedText();
                    for (int i = 0; i < text.length(); ++i) {
                        char oneMore;
                        int colorCode;
                        if (text.charAt(i) != '\u00a7' || i + 1 >= text.length() || (colorCode = "0123456789abcdefklmnorg".indexOf(oneMore = Character.toLowerCase(text.charAt(i + 1)))) >= 16) continue;
                        try {
                            int newColor = Outline.mc.fontRendererObj.colorCode[colorCode];
                            GlStateManager.color((float)(newColor >> 16) / 255.0f, (float)(newColor >> 8 & 255) / 255.0f, (float)(newColor & 255) / 255.0f, 255.0f);
                            continue;
                        }
                        catch (ArrayIndexOutOfBoundsException newColor) {
                            // empty catch block
                        }
                    }
                } else if (((EntityPlayer)entity).isMurderer) {
                    JelloRenderUtil.glColor(Colors.getColor(189, 44, 221));
                } else {
                    JelloRenderUtil.glColor(customColor.getRGB());
                }
                GL11.glPushMatrix();
                GL11.glDisable((int)2929);
                GL11.glLineWidth((float)3.5f);
                GL11.glEnable((int)3042);
                GL11.glEnable((int)2848);
                GL11.glDisable((int)3553);
                double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)Outline.mc.timer.renderPartialTicks;
                double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)Outline.mc.timer.renderPartialTicks;
                double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)Outline.mc.timer.renderPartialTicks;
                GL11.glTranslated((double)(posX - RenderManager.renderPosX), (double)(posY - RenderManager.renderPosY), (double)(posZ - RenderManager.renderPosZ));
                Render entityRender = mc.getRenderManager().getEntityRenderObject(entity);
                if (entityRender != null) {
                    float distance = Outline.mc.thePlayer.getDistanceToEntity(entity);
                    if (entity instanceof EntityLivingBase) {
                        GlStateManager.disableLighting();
                        RendererLivingEntity.renderLayers = false;
                        entityRender.doRender(entity, 0.0, 0.0, 0.0, Outline.mc.timer.renderPartialTicks, Outline.mc.timer.renderPartialTicks);
                        RendererLivingEntity.renderLayers = true;
                        GlStateManager.enableLighting();
                    }
                }
                GL11.glEnable((int)3553);
                GL11.glPopMatrix();
            }
            GL11.glEndList();
            GL11.glPolygonMode((int)1032, (int)6913);
            GL11.glCallList((int)list);
            GL11.glPolygonMode((int)1032, (int)6912);
            GL11.glCallList((int)list);
            Stencil.getInstance().setBuffer(false);
            GL11.glPolygonMode((int)1032, (int)6914);
            GL11.glCallList((int)list);
            Stencil.getInstance().cropInside();
            GL11.glPolygonMode((int)1032, (int)6913);
            GL11.glCallList((int)list);
            GL11.glPolygonMode((int)1032, (int)6912);
            GL11.glCallList((int)list);
            GL11.glPolygonMode((int)1032, (int)6914);
            Stencil.getInstance().stopLayer();
            GL11.glEnable((int)2929);
            GL11.glDeleteLists((int)list, (int)1);
            GL11.glPopMatrix();
        } else if (event instanceof EventRenderEntity && (err = (EventRenderEntity)event).getEntity() != Outline.mc.thePlayer && err.getEntity() instanceof EntityPlayer && err.isPre()) {
            this.draw = true;
            ++this.draws;
        }
    }
}

