package ph.matt.cartservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ph.matt.cartservice.model.CartModel;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM CART")
    List<CartModel> getAllCarts();

}
