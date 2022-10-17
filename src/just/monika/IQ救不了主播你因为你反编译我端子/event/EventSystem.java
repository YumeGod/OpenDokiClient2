/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventAttack;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventBlockBounds;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventChat;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventDamageBlock;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventDeath;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventKeyPress;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventLiquidCollide;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMotion;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMouse;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventMove;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventNametagRender;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventPushBlock;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRender3D;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderEntity;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventRenderGui;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventScreenDisplay;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventSendPacket;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventStep;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventTick;
import just.monika.IQ救不了主播你因为你反编译我端子.event.impl.EventVelocity;

public class EventSystem {
    private static final HashMap<Event, EventSubscription> registry = new HashMap();
    private static final HashMap<Class, Event> instances = new HashMap();

    public static void register(EventListener listener) {
        List<Event> events = EventSystem.getEvents(listener);
        for (Event event : events) {
            EventSubscription<Event> subscription;
            if (EventSystem.isEventRegistered(event)) {
                subscription = registry.get(event);
                subscription.add(listener);
                continue;
            }
            subscription = new EventSubscription<Event>(event);
            subscription.add(listener);
            registry.put(event, subscription);
        }
    }

    public static void unregister(EventListener listener) {
        List<Event> events = EventSystem.getEvents(listener);
        for (Event event : events) {
            if (!EventSystem.isEventRegistered(event)) continue;
            EventSubscription sub = registry.get(event);
            sub.remove(listener);
        }
    }

    public static Event fire(Event event) {
        EventSubscription subscription = registry.get(event);
        if (subscription != null) {
            subscription.fire(event);
        }
        return event;
    }

    public static Event getInstance(Class eventClass) {
        return instances.get(eventClass);
    }

    private static List<Event> getEvents(EventListener listener) {
        ArrayList<Event> events = new ArrayList<Event>();
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(RegisterEvent.class)) continue;
            RegisterEvent ireg = method.getAnnotation(RegisterEvent.class);
            for (Class<? extends Event> eventClass : ireg.events()) {
                events.add(EventSystem.getInstance(eventClass));
            }
        }
        return events;
    }

    private static boolean isEventRegistered(Event event) {
        return registry.containsKey(event);
    }

    static {
        instances.put(EventLiquidCollide.class, new EventLiquidCollide());
        instances.put(EventRenderEntity.class, new EventRenderEntity());
        instances.put(EventStep.class, new EventStep());
        instances.put(EventDamageBlock.class, new EventDamageBlock());
        instances.put(EventSendPacket.class, new EventSendPacket());
        instances.put(EventPushBlock.class, new EventPushBlock());
        instances.put(EventTick.class, new EventTick());
        instances.put(EventDeath.class, new EventDeath());
        instances.put(EventMouse.class, new EventMouse());
        instances.put(EventRender3D.class, new EventRender3D());
        instances.put(EventRenderGui.class, new EventRenderGui());
        instances.put(EventScreenDisplay.class, new EventScreenDisplay());
        instances.put(EventAttack.class, new EventAttack());
        instances.put(EventPacket.class, new EventPacket());
        instances.put(EventVelocity.class, new EventVelocity());
        instances.put(EventMotion.class, new EventMotion());
        instances.put(EventChat.class, new EventChat());
        instances.put(EventBlockBounds.class, new EventBlockBounds());
        instances.put(EventNametagRender.class, new EventNametagRender());
        instances.put(EventMove.class, new EventMove());
        instances.put(EventKeyPress.class, new EventKeyPress());
    }
}

