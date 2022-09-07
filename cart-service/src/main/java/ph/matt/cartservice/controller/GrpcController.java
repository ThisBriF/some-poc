package ph.matt.cartservice.controller;

import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import ph.matt.cart.AllCartsRequest;
import ph.matt.cart.CartGrpcRequestCollection;
import ph.matt.cart.GetAllCartsGrpcServiceGrpc;
import ph.matt.cartservice.service.CartServiceImpl;

@Log4j2
@GRpcService
public class GrpcController extends GetAllCartsGrpcServiceGrpc.GetAllCartsGrpcServiceImplBase {

    @Autowired
    CartServiceImpl cartService;

    @Override
    public void getCarts(AllCartsRequest request, StreamObserver<CartGrpcRequestCollection> responseObserver) {
        responseObserver.onNext(cartService.getCarts(request));
        responseObserver.onCompleted();
    }

}
