package dk.sneakysensation.openartonotifier.event;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 00:56
 */
public enum EventType {
   MESSAGE(0), FRIEND_LOGON(1), FRIEND_REQUEST(2), SERVER_MESSAGE(3), CLIENT_COMMAND(4);

   private int id;

   private EventType(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }
}
