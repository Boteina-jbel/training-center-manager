package org.mql.jee.trainingcenter.dao;

import java.util.List;

import org.mql.jee.trainingcenter.models.Training;

public interface TrainingDao {

    List<Training> selectAll();

    Training selectById(int id);

    void insert(Training training);

    void update(Training training);

    void delete(int id);
}