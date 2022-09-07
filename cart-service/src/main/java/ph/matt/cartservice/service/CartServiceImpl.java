package ph.matt.cartservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ph.matt.cart.AllCartsRequest;
import ph.matt.cart.CartGrpcRequest;
import ph.matt.cart.CartGrpcRequestCollection;
import ph.matt.cartservice.model.CartModel;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CartServiceImpl {

    public CartGrpcRequestCollection getCarts(AllCartsRequest request) {
        log.info("Received AllCartsRequest: {}", request);

        // List<CartModel> results = cartMapper.getAllCarts();
        List<CartModel> results = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            CartModel cartModel = new CartModel();
            cartModel.setId(i);
            results.add(cartModel);
        }

        CartGrpcRequestCollection.Builder responseBuilder = CartGrpcRequestCollection.newBuilder();

        for (int i = 0; i < results.size(); i++) {
            CartGrpcRequest cartRequest = CartGrpcRequest.newBuilder()
                    .setCartId(results.get(i).getId())
                    .build();
            responseBuilder.addCarts(i, cartRequest);
        }

        return responseBuilder.build();
    }

}
