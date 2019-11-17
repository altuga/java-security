package com.itcuties.examples.webapps.filterlogin.beans;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;


/**
 * Simple login bean.
 * 
 * @author itcuties
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 7765876811740798583L;

	// Simple user database :)
	private static final String[] users = {"anna:qazwsx","kate:123456"};
	
	private String username;
	private String password;
    private Boolean rememberme = false;
	
	private boolean loggedIn;

    //@ManagedProperty(value="#{navigationBean}")
    @Named("navigationBean")
    @Inject
    private NavigationBean navigationBean;



	
	/**
	 * Login operation.
	 * @return
	 */
	public String doLogin() {

	    //01

		// Get every user from our sample database :)
		for (String user: users) {
			String dbUsername = user.split(":")[0];
			String dbPassword = user.split(":")[1];
			
			// Successful login
			if (dbUsername.equals(username) && dbPassword.equals(password)) {
			    //02
				loggedIn = true;

				if (rememberme) {
				    putCookie();
                }
				return navigationBean.redirectToWelcome();
			}
		}
		
		// Set login ERROR
		FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
		// To to login page
		return navigationBean.toLogin();
		
	}
	
	/**
	 * Logout operation.
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		loggedIn = false;
		
		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// reset cookie and session
        FacesContext fc = FacesContext.getCurrentInstance();

        HttpServletResponse response = ((HttpServletResponse) (fc.getExternalContext().getResponse()));
        HttpSession session = (HttpSession) (fc.getExternalContext().getSession(false));
        session.setAttribute(username, "");
        Cookie cRandomKey = new Cookie("randomKey", "");
        Cookie cRememberme = new Cookie("rememberme", "");
		
		return navigationBean.toLogin();
	}


    private void putCookie() {
        FacesContext fc = FacesContext.getCurrentInstance();

        HttpServletResponse response = ((HttpServletResponse) (fc.getExternalContext().getResponse()));
        HttpSession session = (HttpSession) (fc.getExternalContext().getSession(false));

        String virtualCheck = "true";
        Cookie cEmail = new Cookie("username", username);
        // Generate random pass
        // put into session


        // 03
        session.setAttribute("username", username);
        session.setAttribute("randomKey", "1234567");

        // 04
        Cookie cRandomKey = new Cookie("randomKey", "1234567");
        Cookie cRememberme = new Cookie("rememberme", virtualCheck);


        response.addCookie(cEmail);
        response.addCookie(cRandomKey); // no more password
        response.addCookie(cRememberme);


    }

	// ------------------------------
	// Getters & Setters 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

    public Boolean getRememberme() {
        return rememberme;
    }

    public void setRememberme(Boolean rememberme) {
        this.rememberme = rememberme;
    }
}
