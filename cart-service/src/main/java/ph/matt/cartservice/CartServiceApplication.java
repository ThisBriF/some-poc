package ph.matt.cartservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Log4j2
@SpringBootApplication
public class CartServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(CartServiceApplication.class, args);
    }

}
