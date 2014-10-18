package main.logger;

import java.util.ArrayList;
import java.util.List;

import main.entity.Apparel;
import main.logger.service.SuitService;

/**
 * Created by TDiva on 18.10.2014.
 */
public class Logger {

    private static SuitService service;

    public static void logSelectedSuitByIds(List<Long> apparelIds) {
        service.addSuit(apparelIds);
    }

    public static void logSelectedSuit(List<Apparel> suit) {
        List<Long> ids = new ArrayList<Long>();
        for (Apparel ap: suit) {
            ids.add(ap.getId());
        }
        service.addSuit(ids);
    }

    public static void logNewItem(Apparel ap) {
        // TODO: need to add logic of adding new clothes
    }

}
