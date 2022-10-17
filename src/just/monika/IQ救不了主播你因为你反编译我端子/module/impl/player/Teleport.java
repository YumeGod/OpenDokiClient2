/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.player;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRender3D;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.util.PlayerUtil;
import just.monika.IQ救不了主播你因为你反编译我端子.util.JelloRenderUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Teleport
extends Module {
    private boolean canTP;
    private int delay;
    private BlockPos endPos;

    public Teleport(ModuleData data) {
        super(data);
    }

    @RegisterEvent(events={EventMotion.class, EventRender3D.class})
    public void onEvent(Event event) {
        EventMotion em;
        if (event instanceof EventMotion && (em = (EventMotion)event).isPre()) {
            if (this.canTP && Mouse.isButtonDown((int)1) && !Teleport.mc.thePlayer.isSneaking() && this.delay == 0 && Teleport.mc.inGameHasFocus && !(Teleport.mc.objectMouseOver.entityHit instanceof Entity)) {
                event.setCancelled(true);
                this.endPos = Teleport.mc.objectMouseOver.getBlockPos();
                double[] startPos = new double[]{Teleport.mc.thePlayer.posX, Teleport.mc.thePlayer.posY, Teleport.mc.thePlayer.posZ};
                PlayerUtil.blinkToPos(startPos, this.endPos, 0.0, new double[]{0.3, 0.2});
                Teleport.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition((double)this.endPos.getX() + 0.5, (double)this.endPos.getY() - 1.0, (double)this.endPos.getZ() + 0.5, false));
                Teleport.mc.thePlayer.setPosition((double)this.endPos.getX() + 0.5, this.endPos.getY() + 1, (double)this.endPos.getZ() + 0.5);
                this.delay = 5;
                event.setCancelled(false);
            }
            if (this.delay > 0) {
                --this.delay;
            }
        }
        if (event instanceof EventRender3D) {
            EventRender3D er = (EventRender3D)event;
            try {
                boolean blockAbove;
                int x = Teleport.mc.objectMouseOver.getBlockPos().getX();
                int y = Teleport.mc.objectMouseOver.getBlockPos().getY();
                int z = Teleport.mc.objectMouseOver.getBlockPos().getZ();
                Block block1 = Teleport.getBlock(x, y, z);
                Block block2 = Teleport.getBlock(x, y + 1, z);
                Block block3 = Teleport.getBlock(x, y + 2, z);
                boolean blockBelow = !(block1 instanceof BlockSign) && block1.getMaterial().isSolid();
                boolean blockLevel = !(block2 instanceof BlockSign) && block1.getMaterial().isSolid();
                boolean bl = blockAbove = !(block3 instanceof BlockSign) && block1.getMaterial().isSolid();
                if (Teleport.getBlock(Teleport.mc.objectMouseOver.getBlockPos()).getMaterial() != Material.air && blockBelow && blockLevel && blockAbove) {
                    this.canTP = true;
                    GL11.glPushMatrix();
                    JelloRenderUtil.pre3D();
                    Teleport.mc.entityRenderer.setupCameraTransform(er.renderPartialTicks, 2);
                    GL11.glColor4d((double)0.0, (double)0.0, (double)0.0, (double)0.25);
                    JelloRenderUtil.drawBoundingBox(new AxisAlignedBB((double)x - RenderManager.renderPosX, (double)y - RenderManager.renderPosY, (double)z - RenderManager.renderPosZ, (double)x - RenderManager.renderPosX + 1.0, (double)y + 1.1 - RenderManager.renderPosY, (double)z - RenderManager.renderPosZ + 1.0));
                    GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    JelloRenderUtil.post3D();
                    GL11.glPopMatrix();
                } else {
                    this.canTP = false;
                }
            }
            catch (Exception x) {
                // empty catch block
            }
        }
    }

    public static Block getBlock(int x, int y, int z) {
        return Teleport.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    public static Block getBlock(BlockPos pos) {
        return Teleport.mc.theWorld.getBlockState(pos).getBlock();
    }
}

