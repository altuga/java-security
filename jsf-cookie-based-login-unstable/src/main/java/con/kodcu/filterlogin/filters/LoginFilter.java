package con.kodcu.filterlogin.filters;

import con.kodcu.filterlogin.beans.LoginBean;
import con.kodcu.filterlogin.utility.CredentialController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter checks if LoginBean has loginIn property set to true.
 * If it is not set then request is being redirected to the login.xhml page.
 * 
 * @author itcuties
 *
 */
public class LoginFilter implements Filter {

	@Inject
	LoginBean loginBean;

	@Named
	@Inject
	private CredentialController credentialController;

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Get the loginBean from session attribute
		//LoginBean loginBean = (LoginBean)((HttpServletRequest)request).getSession().getAttribute("loginBean");
		
		// For the first application request there is no loginBean in the session so user needs to log in
		// For other requests loginBean is present but we need to check if user has logged in successfully
		if (!haveCorrectCookie(request) || loginBean == null || !loginBean.isLoggedIn()  ) {

			 String contextPath = ((HttpServletRequest)request).getContextPath();

			((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
		}

		chain.doFilter(request, response);
			
	}

	private boolean haveCorrectCookie(ServletRequest requestC) {

		if (loginBean!=null && !loginBean.getRememberme()  ) {
			// no need to check session and because remember me is not checked
			return true;
		}

		HttpServletRequest request = (HttpServletRequest) requestC;

		Cookie[] cookiesArr = request.getCookies();

		String usernameFromCookie = null;
		String passwordFromCookie = null;

		// control that if the values
		//07
		if (cookiesArr != null && cookiesArr.length > 0) {
			for (int i = 0; i < cookiesArr.length; i++) {
				String cName = cookiesArr[i].getName();
				String cValue = cookiesArr[i].getValue();
				System.out.println(" cName " + cName + " cValue " + cValue);
				if (cName.equals("username")) {
					usernameFromCookie = cValue;
				} else if (cName.equals("password")) {
					passwordFromCookie = cValue;
				}
			}
		}


		if (usernameFromCookie != null && passwordFromCookie != null) {

			// check session if user has randomKey

			if (credentialController.isUserCredentialAreOkay(usernameFromCookie, passwordFromCookie))

				loginBean.setLoggedIn(true); // a little hack
				// we found the user in session and cookie is matching
				return true;
			}

			// no way , sorry guys.
			return false;


		}


	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}


	
}
