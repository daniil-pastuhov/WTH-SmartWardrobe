package main.logger.repository;

import java.util.ArrayList;
import java.util.List;

import main.logger.entity.Suit;

/**
 * Created by TDiva on 18.10.2014.
 */
public class SuitRepositoryCap implements SuitRepository {

    private List<Suit> cap = new ArrayList<Suit>();

    @Override
    public void save(Suit entity) {
        cap.add(entity);
    }

    @Override
    public List<Suit> getSuits() {
        return cap;
    }

    @Override
    public List<Suit> getSuitById(Long id) {
        List<Suit> res = new ArrayList<Suit>();
        for (Suit s : cap) {
            if (s.getId() == id) {
                res.add(s);
            }
        }
        return res;
    }
}
