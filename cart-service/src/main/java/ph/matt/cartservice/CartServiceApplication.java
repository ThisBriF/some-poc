package ph.matt.cartservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ph.matt.cartservice.service.CartServiceImpl;
import ph.matt.cartservice.service.HelloServiceImpl;

import java.io.IOException;

@Log4j2
@SpringBootApplication
public class CartServiceApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new HelloServiceImpl())
                .addService(new CartServiceImpl()).build();

        log.info("Starting server...");
        server.start();
        log.info("Server started!");
        server.awaitTermination();

        SpringApplication.run(CartServiceApplication.class, args);
    }

}
