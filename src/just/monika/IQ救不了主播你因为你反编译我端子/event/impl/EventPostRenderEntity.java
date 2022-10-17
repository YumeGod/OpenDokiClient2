package just.monika.IQ救不了主播你因为你反编译我端子.event.impl;

import just.monika.IQ救不了主播你因为你反编译我端子.event.Event;
import net.minecraft.entity.Entity;

public class EventPostRenderEntity extends Event{
    private Entity ent;
    
    public EventPostRenderEntity(final Entity ent) {
        this.ent = ent;
    }
    
    public Entity getEntity() {
        return this.ent;
    }
}
