/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.UserEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.UserEntityFacade;

/**
 *
 * @author Olivier
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserEntityFacade facade;

    private UserEntity user;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        user = new UserEntity();
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void register() {
        System.out.println("REGISTER BEGIN");
        UserEntity user = facade.findUser(this.user);
        if (user == null) {
            // We did not find the user in the db
            String password = digestPassword(this.user.getPassword());
            if (password == null) {
                // Could not digest password
                System.out.println("REGISTER FAILED DIGEST FAILED");
                // DO SOMETHING
                return;
            }
            this.user = new UserEntity(this.user.getEmail(), password);
            if (facade.registerUser(this.user)) {
                System.out.println("REGISTER SUCCESS");
                // DO SOMETHING
            } else {
                System.out.println("REGISTER FAILED");
                // DO SOMETHING
            }
        } else {
            System.out.println("REGISTER FAILED USER ALREADY EXIST");
            // DO SOMETHING
        }
    }

    public void login() {
        System.out.println("LOGIN BEGIN");
        UserEntity user = facade.findUser(this.user);
        if (user == null) {
            // We did not find the user in the db
            System.out.println("LOGIN FAILED USER DOES NOT EXIST");
            // DO SOMETHING
        } else {
            String password = digestPassword(this.user.getPassword());
            if (password == null) {
                // Could not digest password
                System.out.println("LOGIN FAILED DIGEST FAILED");
                // DO SOMETHING
                return;
            }
            this.user = new UserEntity(this.user.getEmail(), password);
            if (this.user.getPassword().equals(user.getPassword())) {
                System.out.println("LOGIN SUCCESS");
                // DO SOMETHING
                goToSearch();
            } else {
                System.out.println("LOGIN FAILED WRONG PASSWORD");
                // DO SOMETHING
            }
        }
    }

    private String digestPassword(String password) {
        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, "Could not digest password", ex);
        }
        return null;
    }

    private void goToSearch() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }

}
