package dk.sneakysensation.openartonotifier.event;

import dk.sneakysensation.openartonotifier.protocol.User;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 16:04
 */
public class ConnectEvent implements ArtoEvent{
   private User user;

   public ConnectEvent(User user) {
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public EventType getType() {
      return EventType.CLIENT_COMMAND;
   }
}
