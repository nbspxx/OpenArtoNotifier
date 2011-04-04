package dk.sneakysensation.openartonotifier;

import dk.sneakysensation.openartonotifier.datamodel.Message;
import dk.sneakysensation.openartonotifier.event.ArtoEvent;
import dk.sneakysensation.openartonotifier.event.EventListener;
import dk.sneakysensation.openartonotifier.event.EventType;
import dk.sneakysensation.openartonotifier.event.MessageEvent;
import dk.sneakysensation.openartonotifier.protocol.ArtoAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 01:13
 */
public class Application {
   private static final Integer MAJOR_VERSION = 0;
   private static final Integer MINOR_VERSION = 3;
   private static final Integer BUILD_NUMBER = 9;
   private static final String DEVELOPMENT_STAGE = "b";
   private static final String VERSION_SEPARATOR = ".";

   private ArrayList<Message> messages = new ArrayList<Message>();

   public Application() {
      EventRegistry.addEventListener(new ArtoAccount());
      EventRegistry.addEventListener(new MessageListener(messages));
   }

   /**
    * Gets the messages received during this session.
    *
    * @return the messages received.
    */
   public List<Message> getMessages() {
      return messages;
   }

   /**
    * Gets the version of the application.
    *
    * @return the string representation of the version. This is used in the hello command sent to the server.
    */
   public static String getVersion() {
      return getVersion(MAJOR_VERSION, MINOR_VERSION, BUILD_NUMBER, DEVELOPMENT_STAGE);
   }


   private static String getVersion(Integer major, Integer minor, Integer build, String stage) {
      return major + VERSION_SEPARATOR + minor + VERSION_SEPARATOR + build + VERSION_SEPARATOR + stage;
   }

   /**
    * Listens for messages and adds them to the specified list.
    */
   private static class MessageListener implements EventListener {
      private List<Message> messages;

      public MessageListener(List<Message> messages) {
         this.messages = messages;
      }

      public void eventFired(ArtoEvent event) {
         if (event.getType() == EventType.MESSAGE) {
            Message message = ((MessageEvent) event).getMessage();
            messages.add(message);
         }
      }
   }
}
