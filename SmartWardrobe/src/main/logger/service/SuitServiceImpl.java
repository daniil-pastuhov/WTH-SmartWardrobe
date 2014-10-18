package main.logger.service;

import java.util.List;

import main.logger.entity.Suit;
import main.logger.repository.SuitRepository;

/**
 * Created by TDiva on 18.10.2014.
 */
public class SuitServiceImpl implements SuitService {

    private SuitRepository repository;
    private static long idCounter = 0l;
    private static long getNextId() {
        return idCounter++;
    }



    public SuitRepository getRepository() {
        return repository;
    }

    public void setRepository(SuitRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addSuit(List<Long> apparelId) {
        long id = getNextId();
        for (Long apId: apparelId) {
            repository.save(new Suit(id, apId));
        }
    }

    @Override
    public List<Suit> getSuits() {
        return repository.getSuits();
    }
}
