package com.example.homepage.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ybdevelop on 2016/10/18.
 */
@Entity
@Table(name = "app")
public class App implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String iconurl;

    public App(String name, String iconurl) {
        this.name = name;
        this.iconurl = iconurl;
    }
    public App(){}

    public void update(String name, String iconurl){
        this.name = name;
        this.iconurl = iconurl;
    }
}
