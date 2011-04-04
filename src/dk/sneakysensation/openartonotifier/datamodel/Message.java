package dk.sneakysensation.openartonotifier.datamodel;

import dk.sneakysensation.openartonotifier.event.EventType;
import dk.sneakysensation.openartonotifier.protocol.User;

import java.util.Date;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 15:21
 */
public class Message {
   private Type type;
   private User remoteUser;
   private String messageContent;
   private Date time;

   public Message(Type type, User remoteUser, String messageContent) {
      this.type = type;
      this.remoteUser = remoteUser;
      this.messageContent = messageContent;
      this.time = new Date();
   }

   public Type getType() {
      return type;
   }

   public User getRemoteUser() {
      return remoteUser;
   }

   public String getMessageContent() {
      return messageContent;
   }

   public Date getTime() {
      return time;
   }

   public static enum Type {
      GUEST_BOOK, MAILBOX, FRIEND_NOTE,
   }
}
