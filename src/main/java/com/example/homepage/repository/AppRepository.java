package com.example.homepage.repository;

import com.example.homepage.model.App;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ybdevelop on 2016/10/18.
 */
public interface AppRepository extends CrudRepository<App,Integer>{
    public Iterable<App> findByName(String name);
}
