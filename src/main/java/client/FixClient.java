package client;

import io.allune.quickfixj.spring.boot.starter.EnableQuickFixJClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import quickfix.*;

@EnableQuickFixJClient
@SpringBootApplication
public class FixClient implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(FixClient.class);

    @Autowired
    FixClientMessageSender messageSender;

    public static void main(String[] args) {
        SpringApplication.run(FixClient.class, args);
    }

    public void run(String... args) throws Exception {
        log.info("Joining thread, you can press Ctrl+C to shutdown application");

        //  send message to fix server
        //  and update order id with count
        int count = 1;
        while( count <= 10 ) {
            messageSender.sendMessage(count, null);
            count++;
        }
    }

    @Bean
    public Application clientApplication() {
        return new FixClientApplication();
    }

    @Bean
    public Initiator clientInitiator(quickfix.Application clientApplication, MessageStoreFactory clientMessageStoreFactory,
                                     SessionSettings clientSessionSettings, LogFactory clientLogFactory,
                                     MessageFactory clientMessageFactory) throws ConfigError {

        return new ThreadedSocketInitiator(clientApplication, clientMessageStoreFactory, clientSessionSettings,
                clientLogFactory, clientMessageFactory);
    }
}
