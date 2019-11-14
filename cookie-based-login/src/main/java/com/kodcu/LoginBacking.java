package com.kodcu;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class LoginBacking {

    @NotEmpty
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    @NotEmpty
    @Email(message = "Please provide a valid e-mail")
    private String email;
    private Boolean rememberme = false;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;


    public LoginBacking() {
        checkCookie();
    }

    public void submit() throws IOException {

        FacesContext fc = FacesContext.getCurrentInstance();



        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:

                if(rememberme) {
                    putCookie();
                }

                externalContext.redirect(externalContext.getRequestContextPath() + "/app/index.xhtml");


                break;
            case NOT_DONE:
        }

        this.password = null;

    }

    private void checkCookie() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Cookie[] cookiesArr = ((HttpServletRequest) (fc.getExternalContext().getRequest())).getCookies();

        Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestCookieMap();

        requestCookieMap.keySet();
        requestCookieMap.values();

        if (cookiesArr != null && cookiesArr.length > 0) {
            for (int i = 0; i < cookiesArr.length; i++) {
                String cName = cookiesArr[i].getName();
                String cValue = cookiesArr[i].getValue();
                System.out.println(" cName " + cName + " cValue " + cValue);
                if (cName.equals("email")) {
                    setEmail(cValue);
                } else if (cName.equals("password")) {
                    setPassword(cValue);
                } else if (cName.equals("rememberme")) {
                    setRememberme(new Boolean(cValue));
                    if (getRememberme().equals("false")) {
                        setRememberme(false);
                        setEmail(null);
                        setPassword(null);
                    } else if (getRememberme().equals("true")) {
                        System.out.println("Here in doLogin() line 99");
                        setRememberme(true);
                    }
                }
            }
        }
    }


    private void putCookie() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = ((HttpServletResponse) (fc.getExternalContext().getResponse()));

        String virtualCheck = "true";
        Cookie cEmail = new Cookie("email", email);
        Cookie cPassword = new Cookie("password", password);
        Cookie cRememberme = new Cookie("rememberme", virtualCheck);
        //cEmail.setMaxAge(3600);
        //cPassword.setMaxAge(3600);
        //cRememberme.setMaxAge(3600);

        cEmail.setDomain(request.getContextPath());
        cPassword.setDomain(request.getContextPath());
        cRememberme.setDomain(request.getContextPath());

        response.addCookie(cEmail);
        response.addCookie(cPassword);
        response.addCookie(cRememberme);

    }


    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(email, password))
        );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRememberme() {
        return rememberme;
    }

    public void setRememberme(Boolean rememberme) {
        this.rememberme = rememberme;
    }
}
