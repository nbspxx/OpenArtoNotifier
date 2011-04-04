package dk.sneakysensation.openartonotifier.event;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 15:52
 */
public class RemoteDisconnectEvent implements ArtoEvent {
   public EventType getType() {
      return EventType.SERVER_MESSAGE;
   }
}
