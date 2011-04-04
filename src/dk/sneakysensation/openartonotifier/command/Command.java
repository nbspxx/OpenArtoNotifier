package dk.sneakysensation.openartonotifier.command;

import java.util.List;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 01:15
 */
public interface Command {
   /**
    * Returns the list of strings to send to the server.
    * <p/>
    * Between every entry the output stream is flushed.
    *
    * @return the list of commands. Assumed not null.
    */
   public List<String> getCommand();
}
