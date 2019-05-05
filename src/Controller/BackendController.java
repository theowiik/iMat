package Controller;

import se.chalmers.cse.dat216.project.IMatDataHandler;

/**
 * The controller
 */
public class BackendController {
    private static IMatDataHandler db;

    /**
     *
     */
    public BackendController() {
        db = IMatDataHandler.getInstance();
    }

    /**
     *
     * @return
     */
    public static IMatDataHandler getDb() {
        return db;
    }
}
