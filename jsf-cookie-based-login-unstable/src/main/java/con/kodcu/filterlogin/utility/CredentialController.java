package con.kodcu.filterlogin.utility;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CredentialController  implements Serializable {

    // Simple user database :)
    private static final String[] users = {"anna:qazwsx","kate:123456"};

    public boolean isUserCredentialAreOkay(String userName, String password) {


        for (String user: users) {
            String dbUsername = user.split(":")[0];
            String dbPassword = user.split(":")[1];

            // Successful login
            if (dbUsername.equals(userName) && dbPassword.equals(password)) {
                //02
                return true;



            }

        }

        return false;

    }


}
