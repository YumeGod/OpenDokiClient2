/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.module.impl.other;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import just.monika.IQ救不了主播你因为你反编译我端子.event.RegisterEvent;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.module.Module;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.ModuleData;
import just.monika.IQ救不了主播你因为你反编译我端子.module.data.Setting;
import just.monika.IQ救不了主播你因为你反编译我端子.util.Timer;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ChestStealer
extends Module {
    private String DELAY = "DELAY";
    private String DROP = "DROP";
    private String CLOSE = "CLOSE";
    private String CHESTAURA = "CHESTAURA";
    private just.monika.IQ救不了主播你因为你反编译我端子.util.Timer timer = new Timer();
    private boolean isStealing;

    public ChestStealer(ModuleData data) {
        super(data);
        this.settings.put(this.DELAY, new Setting<Integer>(this.DELAY, 2, "Tick delay before grabbing next item."));
        this.settings.put(this.DROP, new Setting<Boolean>(this.DROP, false, "Auto drop items."));
        this.settings.put(this.CLOSE, new Setting<Boolean>(this.CLOSE, true, "Auto closes chests when done."));
        this.settings.put(this.CHESTAURA, new Setting<Boolean>(this.CHESTAURA, false, "Auto opens chests near you."));
    }

    @RegisterEvent(events={EventMotion.class})
    public void onEvent(Event event) {
        if (event instanceof EventMotion) {
            EventMotion em = (EventMotion)event;
            if (em.isPre() && ((Boolean)((Setting)this.settings.get(this.CHESTAURA)).getValue()).booleanValue()) {
                for (Object o : ChestStealer.mc.theWorld.loadedTileEntityList) {
                    if (!(o instanceof TileEntityChest)) continue;
                    TileEntityChest chest = (TileEntityChest)o;
                    double x = chest.getPos().getX();
                    double y = chest.getPos().getY();
                    double z = chest.getPos().getZ();
                    if (this.isStealing || chest.isEmpty || ChestStealer.mc.thePlayer.getDistance(chest.getPos().getX(), chest.getPos().getY(), chest.getPos().getZ()) >= 4.0 || ChestStealer.mc.thePlayer.worldObj.rayTraceBlocks(new Vec3(x + 0.5, y + 1.0, z + 0.5), new Vec3(ChestStealer.mc.thePlayer.posX, ChestStealer.mc.thePlayer.posY, ChestStealer.mc.thePlayer.posZ)) != null || !ChestStealer.mc.playerController.func_178890_a(ChestStealer.mc.thePlayer, ChestStealer.mc.theWorld, ChestStealer.mc.thePlayer.inventory.getCurrentItem(), chest.getPos(), this.getFacingDirection(chest.getPos()), new Vec3(chest.getPos().getX(), chest.getPos().getY(), chest.getPos().getZ()))) continue;
                    this.isStealing = true;
                    chest.isEmpty = true;
                    break;
                }
            } else if (ChestStealer.mc.currentScreen instanceof GuiChest) {
                GuiChest guiChest = (GuiChest)ChestStealer.mc.currentScreen;
                this.isStealing = true;
                boolean full = true;
                for (ItemStack item : ChestStealer.mc.thePlayer.inventory.mainInventory) {
                    if (item != null) continue;
                    full = false;
                    break;
                }
                boolean containsItems = false;
                if (!full) {
                    int index;
                    ItemStack stack;
                    for (index = 0; index < guiChest.lowerChestInventory.getSizeInventory(); ++index) {
                        stack = guiChest.lowerChestInventory.getStackInSlot(index);
                        if (stack == null) continue;
                        containsItems = true;
                        break;
                    }
                    if (containsItems) {
                        for (index = 0; index < guiChest.lowerChestInventory.getSizeInventory(); ++index) {
                            stack = guiChest.lowerChestInventory.getStackInSlot(index);
                            if (stack == null || !this.timer.delay(50 * ((Number)((Setting)this.settings.get(this.DELAY)).getValue()).intValue())) continue;
                            ChestStealer.mc.playerController.windowClick(guiChest.inventorySlots.windowId, index, 0, (Boolean)((Setting)this.settings.get(this.DROP)).getValue() != false ? 0 : 1, ChestStealer.mc.thePlayer);
                            if (((Boolean)((Setting)this.settings.get(this.DROP)).getValue()).booleanValue()) {
                                ChestStealer.mc.playerController.windowClick(guiChest.inventorySlots.windowId, -999, 0, 0, ChestStealer.mc.thePlayer);
                            }
                            this.timer.reset();
                        }
                    } else if (((Boolean)((Setting)this.settings.get(this.CLOSE)).getValue()).booleanValue()) {
                        ChestStealer.mc.thePlayer.closeScreen();
                    }
                }
            } else {
                this.isStealing = false;
            }
        }
    }

    private EnumFacing getFacingDirection(BlockPos pos) {
        EnumFacing direction = null;
        if (!ChestStealer.mc.theWorld.getBlockState(pos.add(0, 1, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.UP;
        } else if (!ChestStealer.mc.theWorld.getBlockState(pos.add(0, -1, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.DOWN;
        } else if (!ChestStealer.mc.theWorld.getBlockState(pos.add(1, 0, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.EAST;
        } else if (!ChestStealer.mc.theWorld.getBlockState(pos.add(-1, 0, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.WEST;
        } else if (!ChestStealer.mc.theWorld.getBlockState(pos.add(0, 0, 1)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.SOUTH;
        } else if (!ChestStealer.mc.theWorld.getBlockState(pos.add(0, 0, 1)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.NORTH;
        }
        MovingObjectPosition rayResult = ChestStealer.mc.theWorld.rayTraceBlocks(new Vec3(ChestStealer.mc.thePlayer.posX, ChestStealer.mc.thePlayer.posY + (double)ChestStealer.mc.thePlayer.getEyeHeight(), ChestStealer.mc.thePlayer.posZ), new Vec3((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5));
        if (rayResult != null && rayResult.getBlockPos() == pos) {
            return rayResult.field_178784_b;
        }
        return direction;
    }
}

