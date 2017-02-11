package Schedule.Common;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class Email_Authenticator extends Authenticator{
    String userName = null;
    String password = null;
    public Email_Authenticator() {
    }
    public Email_Authenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}

