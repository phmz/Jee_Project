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

/**
 *
 * @author Olivier
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private UserEntity user;
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        user = new UserEntity();
    }
    
    
    
}
