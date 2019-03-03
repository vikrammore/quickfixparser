package client;

import org.springframework.beans.factory.annotation.Autowired;
import quickfix.*;

public class FixClientApplication extends MessageCracker implements Application {


    public void onCreate(SessionID sessionID) {

    }

    public void onLogon(SessionID sessionID) {

    }

    public void onLogout(SessionID sessionID) {

    }

    public void toAdmin(Message message, SessionID sessionID) {

    }

    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {

    }

    public void toApp(Message message, SessionID sessionID) throws DoNotSend {

    }

    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Client App");
        System.out.println( message.toString() );
    }
}
