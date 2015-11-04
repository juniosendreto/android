package br.com.inpe.api.model;

/**
 * Created by junio on 04/11/15.
 */
public class BaseModel {

    public static final String COL_ID = "ID";

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
