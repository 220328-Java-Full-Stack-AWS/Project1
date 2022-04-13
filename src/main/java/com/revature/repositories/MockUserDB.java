package com.revature.repositories;

import com.revature.models.User;

import java.util.HashMap;
import java.util.Map;

public class MockUserDB {
    private Map<String, User> udb;

    private static MockUserDB db;

    private MockUserDB(){
        udb = new HashMap<>();
    }

    static public MockUserDB getInstance(){
        if(db == null){
            db = new MockUserDB();
            return db;
        }
        return db;
    }

    public Map<String, User> getUdb(){
        return udb;
    }
}
