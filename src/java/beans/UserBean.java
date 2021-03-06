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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sessions.UserEntityFacade;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserEntityFacade facade;

    private UserEntity user;

    private boolean isUserLogin = false;

    private String email;

    private String password;

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

    public boolean isIsUserLogin() {
        return isUserLogin;
    }

    public void setIsUserLogin(boolean isUserLogin) {
        this.isUserLogin = isUserLogin;
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

    public void register() {
        System.out.println("REGISTER BEGIN");
        this.user = new UserEntity(email, password);
        UserEntity userTmp = facade.findUser(this.user);
        if (userTmp == null) {
            // We did not find the userTmp in the db
            String passwordDigested = digestPassword(this.user.getPassword());
            if (passwordDigested == null) {
                // Could not digest passwordDigested
                System.out.println("REGISTER FAILED DIGEST FAILED");
                return;
            }
            this.user = new UserEntity(this.user.getEmail(), passwordDigested);
            if (facade.registerUser(this.user)) {
                System.out.println("REGISTER SUCCESS");
                password = passwordDigested;
                isUserLogin = true;
                goToSearch();
            } else {
                System.out.println("REGISTER FAILED");
            }
        } else {
            System.out.println("REGISTER FAILED USER ALREADY EXIST");
        }
    }

    public void login() {
        System.out.println("LOGIN BEGIN");
        this.user = new UserEntity(email, password);
        UserEntity userTmp = facade.findUser(this.user);
        if (userTmp == null) {
            // We did not find the userTmp in the db
            System.out.println("LOGIN FAILED USER DOES NOT EXIST");
            FacesContext.getCurrentInstance().addMessage("login-form:login-email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username and password do not match!"));
            FacesContext.getCurrentInstance().addMessage("login-form:login-password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username and password do not match!"));
        } else {
            String passwordDigested = digestPassword(this.user.getPassword());
            if (passwordDigested == null) {
                // Could not digest passwordDigested
                System.out.println("LOGIN FAILED DIGEST FAILED");
                return;
            }
            this.user = new UserEntity(this.user.getEmail(), passwordDigested);
            if (this.user.getPassword().equals(userTmp.getPassword())) {
                System.out.println("LOGIN SUCCESS");
                password = passwordDigested;
                isUserLogin = true;
                goToSearch();
            } else {
                System.out.println("LOGIN FAILED WRONG PASSWORD");
                FacesContext.getCurrentInstance().addMessage("login-form:login-email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username and password do not match!"));
                FacesContext.getCurrentInstance().addMessage("login-form:login-password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username and password do not match!"));
            }
        }
    }

    public void logout() {
        System.out.println("LOGOUT BEGIN");
        user = null;
        email = "";
        password = "";
        isUserLogin = false;
        goToLogin();
        System.out.println("LOGOUT SUCCESS");
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

    public void goToSearch() {
        if (!isUserLogin) {
            return;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/search.xhtml?faces-redirect=true");
    }

    private void goToLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/login.xhtml?faces-redirect=true");
    }

    public void goToSearchHistory() {
        if (!isUserLogin) {
            return;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/searchhistory.xhtml?faces-redirect=true");
    }

    public void goToCommentHistory() {
        if (!isUserLogin) {
            return;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/commenthistory.xhtml?faces-redirect=true");
    }

    public void checkLoginAccess() {
        if (isUserLogin) {
            goToSearch();
        }
    }

    public void checkOtherAccess() {
        if (!isUserLogin) {
            goToLogin();
        }
    }

}
