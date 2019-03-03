package server;

import org.springframework.beans.factory.annotation.Autowired;
import quickfix.*;

public class FixServerApplication extends MessageCracker implements Application {

    @Autowired
    FixServerMessageReceiver messageReceiver;

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
        // crack will delegate to
        // specific implementation of on message
        // depending on specific fix version message
        crack( message, sessionID );
    }

    // handler to support fix50 new order
    public void onMessage(quickfix.fix50.NewOrderSingle message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        messageReceiver.process( message, sessionID );
    }
}
