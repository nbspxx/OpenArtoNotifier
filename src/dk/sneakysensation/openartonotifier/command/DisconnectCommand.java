package dk.sneakysensation.openartonotifier.command;

import dk.sneakysensation.openartonotifier.protocol.Constants;

import java.util.Arrays;
import java.util.List;

/**
 * DisconnectCommand the command sent to disconnect
 *
 * @author Jesenko
 */
public class DisconnectCommand implements Command {

   public List<String> getCommand() {
      return Arrays.asList(Constants.LOGIN_PROCESS_PREFIX, "DISCONNECT");
   }
}
