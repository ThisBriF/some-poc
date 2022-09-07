package ph.matt.cartgateway.controller;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.matt.cart.AllCartsRequest;
import ph.matt.cart.CartGrpcRequestCollection;
import ph.matt.cart.GetAllCartsGrpcServiceGrpc;
import ph.matt.cartgateway.model.CartGatewayResponse;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
public class CartGatewayController {

    @GetMapping("/carts")
    public ResponseEntity<?> hello() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        GetAllCartsGrpcServiceGrpc.GetAllCartsGrpcServiceBlockingStub stub =
                GetAllCartsGrpcServiceGrpc.newBlockingStub(channel);

        CartGrpcRequestCollection response = stub.getCarts(
                AllCartsRequest.newBuilder().build()
        );

        List<CartGatewayResponse> convertedResponse = response.getCartsList().stream()
                .map(cartGrpcRequest -> new CartGatewayResponse(cartGrpcRequest.getCartId()))
                .collect(Collectors.toList());

        channel.shutdown();

        return ResponseEntity.ok(convertedResponse);
    }

}
