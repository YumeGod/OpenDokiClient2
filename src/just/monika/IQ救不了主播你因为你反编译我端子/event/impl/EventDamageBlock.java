/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import net.minecraft.util.BlockPos;

public class EventDamageBlock
extends Event {
    private BlockPos currentBlock;

    public void fire(BlockPos b) {
        this.setCurrentBlock(b);
        super.fire();
    }

    public BlockPos getCurrentBlock() {
        return this.currentBlock;
    }

    public void setCurrentBlock(BlockPos currentBlock) {
        this.currentBlock = currentBlock;
    }
}

