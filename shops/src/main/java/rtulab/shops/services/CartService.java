package rtulab.shops.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rtulab.shops.models.dto.GoodInCart;
import rtulab.shops.models.mongoDocuments.Cart;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.repositories.CartRepository;
import rtulab.shops.repositories.GoodRepository;
import rtulab.shops.services.exceptions.TooManyBoughtGoodsException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private GoodRepository goodRepository;

    public Cart get(String id) {
        return cartRepository.getById(id);
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public List<Cart> create(Cart cart) {
        cartRepository.insert(cart);
        return cartRepository.findAll();
    }

    public Cart getByToken(String token) {
        String username = JWT.decode(token.substring(7)).getSubject();
        return cartRepository.getByUsername(username);
    }

    public Cart update(String id, Cart newCart) {
        newCart.setId(id);
        return cartRepository.save(newCart);
    }

    public List<Cart> delete(String id) {
        cartRepository.deleteById(id);
        return cartRepository.findAll();
    }

    public Cart putGoodInCart(String token, GoodInCart goodInCart) throws TooManyBoughtGoodsException {
        String username = JWT.decode(token.substring(7)).getSubject();
        Cart cart = cartRepository.getByUsername(username);
        if(cart==null) {
            cart = new Cart();
            cart.setId(Integer.toString(username.toLowerCase().hashCode()));
            cart.setUsername(username);
            cart.setBoughtGoods(new ArrayList<>());
            cartRepository.save(cart);
        }
        Good good = goodRepository.getById(goodInCart.getGoodId());
        if(good.getAmount() >= goodInCart.getBoughtAmount()) {
            cart.getBoughtGoods().add(goodInCart);
            return cart;
        }
        else
            throw new TooManyBoughtGoodsException();
    }
}
