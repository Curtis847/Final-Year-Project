package com.advisor.health.mental.mentalhealthadvisor;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by curti on 03/12/2017.
 */

public class User {


    public String name;
    public String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
