package org.mql.jee.trainingcenter.dao;

import java.util.List;
import org.mql.jee.trainingcenter.models.Trainer;

public interface TrainerDao {
    List<Trainer> selectAll();

    Trainer selectById(int id);

    void insert(Trainer student);

    void update(Trainer student);

    void delete(int id);
}
