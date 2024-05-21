package model;

/**
 *
 * @author PhamSon
 */
public class Account {
    private int acc_ID;
    private String name;
    private String phone_number;
    private String email;
    private String password;
    private int roleID;
    private boolean acc_status;
    private String avatar_url;

    public Account() {
    }

    public Account(int acc_ID, String name, String phone_number, String email, String password, boolean acc_status,int roleID ,String avatar_url) {
        this.acc_ID = acc_ID;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.acc_status = acc_status;
        this.roleID = roleID;
        this.avatar_url = avatar_url;
        
    }

    public int getAcc_ID() {
        return acc_ID;
    }

    public void setAcc_ID(int acc_ID) {
        this.acc_ID = acc_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAcc_status() {
        return acc_status;
    }

    public void setAcc_status(boolean acc_status) {
        this.acc_status = acc_status;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    
    
}
