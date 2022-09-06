package ph.matt.cartgateway.controller;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.matt.cart.AllCartsRequest;
import ph.matt.cart.CartRequestCollection;
import ph.matt.cart.GetAllCartsServiceGrpc;

@Log4j2
@RestController
public class CartGatewayController {

    @GetMapping("/carts")
    public String hello() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        /*HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Baeldung")
                .setLastName("gRPC")
                .build());

        System.out.println("Response received from server:\n" + helloResponse);*/

        GetAllCartsServiceGrpc.GetAllCartsServiceBlockingStub stub = GetAllCartsServiceGrpc.newBlockingStub(channel);
        CartRequestCollection response = stub.getCarts(
                AllCartsRequest.newBuilder().build()
        );

        log.info(response);

        channel.shutdown();

        return "Hello";
    }

}
