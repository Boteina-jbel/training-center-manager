package org.mql.jee.trainingcenter.context;

import java.util.Hashtable;

public class Model {

    private Hashtable<String, Object> models;

    public Model() {
        models = new Hashtable<String, Object>();
    }

    public void setModel(String key, Object value) {
        models.put(key, value);
    }

    public Object getModel(String key) {
        return models.get(key);
    }
}