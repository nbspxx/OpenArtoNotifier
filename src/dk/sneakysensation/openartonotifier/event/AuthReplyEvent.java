package dk.sneakysensation.openartonotifier.event;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 15:39
 */
public class AuthReplyEvent implements ArtoEvent{
   private boolean authorized;
   private String userId;

   public AuthReplyEvent(boolean authorized, String userId) {
      this.authorized = authorized;
      this.userId = userId;
   }

   public boolean isAuthorized() {
      return authorized;
   }

   public String getUserId() {
      return userId;
   }

   public EventType getType() {
      return EventType.SERVER_MESSAGE;
   }
}
