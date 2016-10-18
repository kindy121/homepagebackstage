package com.example.homepage.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ybdevelop on 2016/10/18.
 */
@Entity
@Table(name = "tab")
public class Tab implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String href;

    public Tab(String name, String href) {
        this.name = name;
        this.href = href;
    }
    public Tab(){}

    public void update(String name, String href){
        this.name = name;
        this.href = href;
    }


}
