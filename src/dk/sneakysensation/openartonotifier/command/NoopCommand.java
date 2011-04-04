package dk.sneakysensation.openartonotifier.command;

import dk.sneakysensation.openartonotifier.protocol.Constants;

import java.util.Arrays;
import java.util.List;

/**
 * NoopCommand the NO-OP command to ensure the connection does not timeout.
 *
 * @author Jesenko
 */
public class NoopCommand implements Command {
   public List<String> getCommand() {
      return Arrays.asList(Constants.NOOP_PREFIX, "NOOP");
   }
}
