package com.teamdevroute.devroute.video;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
//@Setter
public class Technology_stack {
    @Id
    @GeneratedValue
    @Column(name = "technology_stack_id")
    private Long id;
    private String name;
    private Long count;
    public Technology_stack(){

    }
    public Long getAddedCount(){
        return count + 1;
    }


}
