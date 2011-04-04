package dk.sneakysensation.openartonotifier.ui;

import dk.sneakysensation.openartonotifier.Application;
import dk.sneakysensation.openartonotifier.event.EventListener;

/**
 * TODO[Jesenko] - someone remind me to document this class
 *
 * @author Jesenko
 * @since 21-03-11, 16:28
 */
public interface UserInterface extends EventListener {
   public void initialize(Application application);
}
