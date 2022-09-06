package ph.matt.cartservice.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import ph.matt.cart.AllCartsRequest;
import ph.matt.cart.CartRequest;
import ph.matt.cart.CartRequestCollection;
import ph.matt.cart.GetAllCartsServiceGrpc;
import ph.matt.cartservice.mapper.CartMapper;
import ph.matt.cartservice.model.CartModel;

import java.util.List;

@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class CartServiceImpl extends GetAllCartsServiceGrpc.GetAllCartsServiceImplBase {

    private CartMapper cartMapper;

    @Override
    public void getCarts(AllCartsRequest request, StreamObserver<CartRequestCollection> responseObserver) {
        log.info("Received request: {}", request);

        List<CartModel> results = cartMapper.getAllCarts();

        CartRequestCollection.Builder responseBuilder = CartRequestCollection.newBuilder();

        for (int i = 0; i < results.size(); i++) {
            CartRequest cartRequest = CartRequest.newBuilder()
                    .setCartId(results.get(i).getId())
                    .build();
            responseBuilder.addCarts(i, cartRequest);
        }

        CartRequestCollection response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
