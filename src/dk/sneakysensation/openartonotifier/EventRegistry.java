package dk.sneakysensation.openartonotifier;

import dk.sneakysensation.openartonotifier.event.ArtoEvent;
import dk.sneakysensation.openartonotifier.event.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 15:35
 */
public class EventRegistry {
   private static EventRegistry ourInstance = new EventRegistry();
   private List<EventListener> listeners = new ArrayList<EventListener>();

   public static EventRegistry getInstance() {
      return ourInstance;
   }


   /**
    * Adds an event listener.
    *
    * @param listener the event listener to add.
    */
   public static void addEventListener(EventListener listener) {
      getInstance().getListeners().add(listener);
   }

   /**
    * Fires an event to all the registered event listeners.
    *
    * @param event the event to fire.
    */
   public static void fireEvent(ArtoEvent event) {
      for (EventListener listener : getInstance().getListeners()) {
         listener.eventFired(event);
      }
   }

   private List<EventListener> getListeners() {
      return listeners;
   }

   private EventRegistry() {
   }
}
