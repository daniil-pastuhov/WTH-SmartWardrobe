package main.logger.repository;

import java.util.List;

import main.logger.entity.Suit;

/**
 * Created by TDiva on 18.10.2014.
 */
public interface SuitRepository {

    void save(Suit entity);
    List<Suit> getSuits();
    List<Suit> getSuitById(Long id);
}
