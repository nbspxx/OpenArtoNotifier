package dk.sneakysensation.openartonotifier.event;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 16:14
 */
public class DisconnectEvent implements ArtoEvent{
   public EventType getType() {
      return EventType.CLIENT_COMMAND;
   }
}
