package com.springfield.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "involvement")
@IdClass(InvolvementID.class)
//@NamedQuery(name = "Involvement.findByEmailAddress",
//        query = "select i.user_id from Involvement i where i.module_id = ?1")
public class Involvement {
    @Id
    @NotBlank
    private Long user_id;

    @Id
    @NotBlank
    private Long module_id;


    public Involvement(){ }
    public Involvement(Long user_id, Long module_id) {
        super();
        this.user_id = user_id;
        this.module_id = module_id;
    }

    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long id_book) {
        this.user_id = user_id;
    }

    public Long getModule_id() {
        return module_id;
    }
    public void setModule_id(Long module_id) { this.module_id = module_id; }

}
