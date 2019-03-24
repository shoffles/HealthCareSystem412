/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;


class UserInfo {
    
    private String type;
    private String password;
    private String name;
    private String DOB;
    
    public UserInfo(String type, String password, String name, String DOB) {
        this.type = type;
        this.password = password;
        this.name = name;
        this.DOB = DOB;
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
