/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class PlayerUtil
implements MinecraftUtil {
    public static boolean isInLiquid() {
        if (mc.thePlayer == null) {
            return false;
        }
        for (int x = MathHelper.floor_double((double) mc.thePlayer.boundingBox.minX); x < MathHelper.floor_double(mc.thePlayer.boundingBox.maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double((double) mc.thePlayer.boundingBox.minZ); z < MathHelper.floor_double(mc.thePlayer.boundingBox.maxZ) + 1; ++z) {
                BlockPos pos = new BlockPos(x, (int) mc.thePlayer.boundingBox.minY, z);
                Block block = mc.theWorld.getBlockState(pos).getBlock();
                if (block == null || block instanceof BlockAir) continue;
                return block instanceof BlockLiquid;
            }
        }
        return false;
    }

    public static boolean isOnLiquid() {
        AxisAlignedBB boundingBox = mc.thePlayer.getEntityBoundingBox();
        if (boundingBox == null) {
            return false;
        }
        boundingBox = boundingBox.contract(0.01, 0.0, 0.01).offset(0.0, -0.01, 0.0);
        boolean onLiquid = false;
        int y = (int)boundingBox.minY;
        for (int x = MathHelper.floor_double((double)boundingBox.minX); x < MathHelper.floor_double(boundingBox.maxX + 1.0); ++x) {
            for (int z = MathHelper.floor_double((double)boundingBox.minZ); z < MathHelper.floor_double(boundingBox.maxZ + 1.0); ++z) {
                Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block == Blocks.air) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                onLiquid = true;
            }
        }
        return onLiquid;
    }

    public static void blinkToPos(double[] startPos, BlockPos endPos, double slack, double[] pOffset) {
        double curX = startPos[0];
        double curY = startPos[1];
        double curZ = startPos[2];
        try {
            double endX = (double)endPos.getX() + 0.5;
            double endY = (double)endPos.getY() + 1.0;
            double endZ = (double)endPos.getZ() + 0.5;
            double distance = Math.abs(curX - endX) + Math.abs(curY - endY) + Math.abs(curZ - endZ);
            int count = 0;
            while (distance > slack) {
                distance = Math.abs(curX - endX) + Math.abs(curY - endY) + Math.abs(curZ - endZ);
                if (count <= 120) {
                    double offset;
                    boolean next = false;
                    double diffX = curX - endX;
                    double diffY = curY - endY;
                    double diffZ = curZ - endZ;
                    double d = offset = (count & 1) == 0 ? pOffset[0] : pOffset[1];
                    if (diffX < 0.0) {
                        curX = Math.abs(diffX) > offset ? (curX += offset) : (curX += Math.abs(diffX));
                    }
                    if (diffX > 0.0) {
                        curX = Math.abs(diffX) > offset ? (curX -= offset) : (curX -= Math.abs(diffX));
                    }
                    if (diffY < 0.0) {
                        curY = Math.abs(diffY) > 0.25 ? (curY += 0.25) : (curY += Math.abs(diffY));
                    }
                    if (diffY > 0.0) {
                        curY = Math.abs(diffY) > 0.25 ? (curY -= 0.25) : (curY -= Math.abs(diffY));
                    }
                    if (diffZ < 0.0) {
                        curZ = Math.abs(diffZ) > offset ? (curZ += offset) : (curZ += Math.abs(diffZ));
                    }
                    if (diffZ > 0.0) {
                        curZ = Math.abs(diffZ) > offset ? (curZ -= offset) : (curZ -= Math.abs(diffZ));
                    }
                    Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(curX, curY, curZ, true));
                    ++count;
                    continue;
                }
                break;
            }
        }
        catch (Exception endX) {
            // empty catch block
        }
    }

    public static boolean isMoving() {
        if (!mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isSneaking()) {
            return mc.thePlayer.movementInput.moveForward != 0.0f || mc.thePlayer.movementInput.moveStrafe != 0.0f;
        }
        return false;
    }
}

