package con.kodcu.filterlogin.beans;

import con.kodcu.filterlogin.utility.CredentialController;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

	@Named("credentialController")
	@Inject
	private CredentialController credentialController;
	
	private String username;
	private String password;
	private Boolean rememberme = true;
	
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

		// Successful login
		if (credentialController.isUserCredentialAreOkay(username, password)) {
			    //02
				loggedIn = true;

				if (rememberme) {
				    putCookie();
                }
				return navigationBean.redirectToWelcome();
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
		HttpServletRequest request = ((HttpServletRequest) (fc.getExternalContext().getRequest()));


		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setDomain("localhost");
					cookie.setPath("/login-unstable");
					response.addCookie(cookie);

				}
				if (cookie.getName().equals("password")) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setDomain("localhost");
					cookie.setPath("/login-unstable");
					response.addCookie(cookie);
				}
				if (cookie.getName().equals("cRememberme")) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setDomain("localhost");
					cookie.setPath("/login-unstable");
					response.addCookie(cookie);
				}
			}

		}




		return navigationBean.toLogin();
	}


    private void putCookie() {
        FacesContext fc = FacesContext.getCurrentInstance();

        HttpServletResponse response = ((HttpServletResponse) (fc.getExternalContext().getResponse()));
        HttpSession session = (HttpSession) (fc.getExternalContext().getSession(false));

        String virtualCheck = "true";

		// Generate random pass
        // put into session


        // 04
		Cookie cUsername = new Cookie("username", username);
		Cookie cPassword = new Cookie("password", password);
		Cookie cRememberme = new Cookie("cRememberme", virtualCheck);


		response.addCookie(cUsername);
		response.addCookie(cPassword);
		response.addCookie(cPassword);
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
