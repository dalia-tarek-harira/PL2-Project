/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2.project;

/**
 *
 * @author roaam
 */
public abstract class User {

    protected int id;
    protected String name;
    protected String password;
    protected String email;
    protected String number;
    protected String role;

    protected User() {

    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setNumber(String number) {
        this.number = number;
    }

    protected void setRole(String role) {
        this.role = role;
    }

    protected int getId() {
        return this.id;
    }

    protected String getName() {
        return this.name;
    }

    protected String getPassword() {
        return this.password;
    }

    protected String getEmail() {
        return this.email;
    }

    protected String getNumber() {
        return this.number;
    }

    protected String getRole() {
        return this.role;
    }

    public abstract void showBugs();
}
