package main.logger.service;

import java.util.List;

import main.logger.entity.Suit;

/**
 * Created by TDiva on 18.10.2014.
 */
public interface SuitService {

    void addSuit(List<Long> apparelId);

    List<Suit> getSuits();
}
