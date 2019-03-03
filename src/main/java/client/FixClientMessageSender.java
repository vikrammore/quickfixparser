package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import quickfix.Initiator;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix50.NewOrderSingle;
import quickfix.mina.SessionConnector;

@Component
public class FixClientMessageSender {

    @Autowired
    private Initiator sessionConnector;

    public void sendMessage(int orderId, SessionID sessionID) {

        if ( sessionID == null || !Session.doesSessionExist( sessionID ) ) {
            sessionID = sessionConnector.getSessions().get(0);
            bookSingleOrder( orderId, sessionID );
        }
    }

    private void bookSingleOrder(int clOrderId, SessionID sessionID){
        //In real world this won't be a hardcoded value rather than a sequence.
        ClOrdID orderId = new ClOrdID(clOrderId+"");
        //to be executed on the exchange
        HandlInst instruction = new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION);
        //Since its FX currency pair name
        Symbol mainCurrency = new Symbol("EUR/USD");
        //Which side buy, sell
        Side side = new Side(Side.BUY);
        //Time of transaction
        TransactTime transactionTime = new TransactTime();
        //Type of our order, here we are assuming this is being executed on the exchange
        OrdType orderType = new OrdType(OrdType.MARKET);
        NewOrderSingle newOrderSingle = new NewOrderSingle(orderId, side, transactionTime,orderType);
        //Quantity
        newOrderSingle.set(new OrderQty(100));
        newOrderSingle.set( mainCurrency );
        newOrderSingle.set( instruction );

        try {
            Session.sendToTarget(newOrderSingle, sessionID);
        } catch (SessionNotFound e) {
            e.printStackTrace();
        }
    }
}
