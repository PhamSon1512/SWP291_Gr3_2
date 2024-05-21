/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PhamSon
 */
public class Role {
    private int role_ID;
    private String role_name;
    private int role_status;

    public Role() {
    }

    public Role(int role_ID, String role_name, int role_status) {
        this.role_ID = role_ID;
        this.role_name = role_name;
        this.role_status = role_status;
    }

    public int getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getRole_status() {
        return role_status;
    }

    public void setRole_status(int role_status) {
        this.role_status = role_status;
    }
}
