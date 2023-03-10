package dto;

import java.io.Serializable;
import java.util.Date;

public class SaveData implements Serializable {

    private Date date;
    private Owner owner;

    public SaveData(Owner owner) {
        this.date = new Date(System.currentTimeMillis());
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public Owner getOwner() {
        return owner;
    }
}
