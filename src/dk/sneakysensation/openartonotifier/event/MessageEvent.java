package dk.sneakysensation.openartonotifier.event;

import dk.sneakysensation.openartonotifier.datamodel.Message;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 15:20
 */
public class MessageEvent implements ArtoEvent {
   private Message message;

   public MessageEvent(Message message) {
      this.message = message;
   }

   public Message getMessage() {
      return message;
   }

   public EventType getType() {
      return EventType.MESSAGE;
   }
}
