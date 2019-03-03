package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import quickfix.FieldNotFound;
import quickfix.SessionID;

@Component
public class FixServerMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(FixServerMessageReceiver.class);

    // message processor to process Fix50 new single order message
    public void process(quickfix.fix50.NewOrderSingle message, SessionID sessionId ) throws FieldNotFound {
        log.info( "Message received: {}, SessionId: {}", message, sessionId );
        log.info( "SessionInfo: {}", sessionId.toString() );
        log.info( "Order.Side:{}, Order.Id: {}", message.getSide(), message.getClOrdID());
        log.info( "Order.Qty: {}, Order.Inst: {}", message.getOrderQty(), message.getHandlInst());
    }
}
