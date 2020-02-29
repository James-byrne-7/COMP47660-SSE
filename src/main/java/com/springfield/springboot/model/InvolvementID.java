package com.springfield.springboot.model;

import java.io.Serializable;
import java.util.Objects;

public class InvolvementID implements Serializable {

    Long user_id;
    Long module_id;

    public InvolvementID(){}

    public InvolvementID( Long user_id, Long module_id){
        this.user_id = user_id;
        this.module_id = module_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvolvementID involvementID = (InvolvementID) o;
        return user_id.equals(involvementID.user_id) &&
                module_id.equals(involvementID.module_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, module_id);
    }

    public Long getUser_id(){
        return user_id;
    }
    public Long getModule_id(){
        return module_id;
    }
}
