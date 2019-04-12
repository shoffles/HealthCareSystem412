/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;
import java.lang.System;

class User {
    
    private long userId;
    private String username;
    private String type;
    private String password;
    private String name;
    private String DOB;
    
    public User(long userID, String username, String type, String password, String name, String DOB) {
        this.userId = userID;
        this.username = username;
        this.type = type;
        this.password = password;
        this.name = name;
        this.DOB = DOB;
    }

    public long getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDOB() {
        return DOB;
    }
    
    
    
    
}
