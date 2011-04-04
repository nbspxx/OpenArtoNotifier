package dk.sneakysensation.openartonotifier.protocol;

import dk.sneakysensation.openartonotifier.EventRegistry;
import dk.sneakysensation.openartonotifier.command.Command;
import dk.sneakysensation.openartonotifier.command.DisconnectCommand;
import dk.sneakysensation.openartonotifier.command.LoginCommand;
import dk.sneakysensation.openartonotifier.command.NoopCommand;
import dk.sneakysensation.openartonotifier.datamodel.Message;
import dk.sneakysensation.openartonotifier.event.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 00:52
 */
public class ArtoAccount implements EventListener {
   private User user;
   private PrintWriter writer;

   private static final String host = "194.152.39.169";
   private static final int port = 80;
   private static final int NO_OP_TIMEOUT = 60;

   public boolean connect() {
      try {
         Socket socket = new Socket(host, port);
         writer = new PrintWriter(socket.getOutputStream());
         new Thread(new WorkerThread(socket.getInputStream())).start();

         sendCommand(new LoginCommand(user.getUsername(), user.getPassword()));

         Timer timer = new Timer("NO-OP command sender");
         timer.schedule(new NoopTimerTask(), NO_OP_TIMEOUT * 1000);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }

   private void sendCommand(Command command) {
      writer.flush();
      for (String subCommand : command.getCommand()) {
         writer.print(subCommand);
         writer.flush();
      }
   }

   public void setUser(User user) {
      this.user = user;
   }

   public void eventFired(ArtoEvent event) {
      if (event instanceof ConnectEvent) {
         setUser(((ConnectEvent) event).getUser());
         connect();
      }
      if (event instanceof DisconnectEvent) {
         sendCommand(new DisconnectCommand());
      }
   }

   public void disconnect() {
      sendCommand(new DisconnectCommand());
      EventRegistry.fireEvent(new DisconnectEvent());
   }

   private class WorkerThread implements Runnable {
      private Scanner scanner;

      private WorkerThread(InputStream stream) {
         scanner = new Scanner(stream).useDelimiter(String.valueOf(Constants.SEPARATOR));
      }

      public void run() {
         while (scanner.hasNext()) {
            String line = scanner.next();

            handleAuthReply(line);
            handleMessages(line);
            handleDisconnect(line);
         }
      }

      private void handleDisconnect(String line) {
         if (line.contains("DISCONNECT")) {
            EventRegistry.fireEvent(new RemoteDisconnectEvent());
         }
      }

      private void handleMessages(String line) {
         if (line.contains("GBN") || line.contains("KBN") || line.contains("VN")) {
            String remoteUserId = scanner.next();
            String remoteUserName = scanner.next();

            User remoteUser = createUser(remoteUserId, remoteUserName);

            String content = null;
            if (!line.contains("VN")) {
               content = scanner.next();
            }
            Message.Type messageType = null;
            if (line.contains("GBN")) {
               messageType = Message.Type.GUEST_BOOK;
            } else if (line.contains("KBN")) {
               messageType = Message.Type.MAILBOX;
            } else if (line.contains("VN")) {
               messageType = Message.Type.FRIEND_NOTE;
            }
            Message message = new Message(messageType, remoteUser, content);
            EventRegistry.fireEvent(new MessageEvent(message));
         }
      }

      private User createUser(String remoteUserId, String remoteUserName) {
         User remoteUser = new User(remoteUserName, null);
         remoteUser.setUserId(remoteUserId);
         return remoteUser;
      }

      private void handleAuthReply(String line) {
         if (line.contains("AUTHREPLY")) {
            scanner.next();
            String sessionId = scanner.next();
            if (sessionId.contains("-0000-")) {
               EventRegistry.fireEvent(new AuthReplyEvent(false, null));
            } else {
               String userId = scanner.next();
               EventRegistry.fireEvent(new AuthReplyEvent(true, userId));
            }
         }
      }
   }

   private class NoopTimerTask extends TimerTask {
      @Override
      public void run() {
         sendCommand(new NoopCommand());
         EventRegistry.fireEvent(new NoOpEvent());
      }
   }
}
