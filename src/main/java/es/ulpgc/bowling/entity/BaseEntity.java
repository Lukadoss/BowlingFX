package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @TableGenerator(name = "tab", initialValue = 100)
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab")
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
