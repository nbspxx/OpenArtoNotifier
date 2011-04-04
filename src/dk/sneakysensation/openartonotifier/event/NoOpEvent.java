package dk.sneakysensation.openartonotifier.event;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 19:44
 */
public class NoOpEvent implements ArtoEvent{
   public EventType getType() {
      return EventType.CLIENT_COMMAND;
   }
}
