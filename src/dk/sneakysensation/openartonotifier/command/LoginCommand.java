package dk.sneakysensation.openartonotifier.command;

import dk.sneakysensation.openartonotifier.Application;

import java.util.Arrays;
import java.util.List;

import static dk.sneakysensation.openartonotifier.protocol.Constants.LOGIN_PROCESS_PREFIX;
import static dk.sneakysensation.openartonotifier.protocol.Constants.SEPARATOR;

/**
 * LoginCommand the command sent to login.
 *
 * @author Jesenko Mehmedbasic
 */
public class LoginCommand implements Command {
   private static final String keyword = "LOGIN";
   private final String command;

   public LoginCommand(String username, String password) {
      command = keyword + SEPARATOR + username + SEPARATOR + password + SEPARATOR + Application.getVersion();
   }

   public List<String> getCommand() {
      return Arrays.asList(LOGIN_PROCESS_PREFIX, command);
   }
}
