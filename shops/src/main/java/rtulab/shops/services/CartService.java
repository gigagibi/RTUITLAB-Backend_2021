package rtulab.shops.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
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
        Good good = goodRepository.getById(goodInCart.getGoodId());
        if(cart!=null) { //if cart exists
            if(cart.getBoughtGoods().stream().anyMatch(g -> g.getGoodId().equals(goodInCart.getGoodId()))) { //if good is already in cart, increase amount of it and replace good in cart
                goodInCart.setBoughtAmount(
                        goodInCart.getBoughtAmount() + cart.getBoughtGoods()
                        .stream()
                        .filter(g -> g.getGoodId().equals(goodInCart.getGoodId()))
                        .findAny()
                        .orElse(new GoodInCart())
                        .getBoughtAmount()
                );
                int oldIndex = cart.getBoughtGoods().indexOf(cart.getBoughtGoods().stream().filter(g -> g.getGoodId().equals(goodInCart.getGoodId())).findAny().get());
                cart.getBoughtGoods().set(oldIndex, goodInCart);
            }
            else {
                cart.getBoughtGoods().add(goodInCart);
            }
        }
        else { //if user doesn't have a cart, creates it and put good
            cart = new Cart();
            cart.setId(Integer.toString(username.toLowerCase().hashCode()));
            cart.setUsername(username);
            cart.setBoughtGoods(new ArrayList<>());
            cart.getBoughtGoods().add(goodInCart);
        }
        if(good.getAmount() >= goodInCart.getBoughtAmount()) { //in 3rd case, when user have a cart but there is no good that he's trying to add, its just put good in cart
            return cartRepository.save(cart);
        }
        else
            throw new TooManyBoughtGoodsException();
    }

    public Cart removeGoodFromCart(String token, GoodInCart goodInCart) throws Exception {
        String username = JWT.decode(token.substring(7)).getSubject();
        Cart cart = cartRepository.getByUsername(username);
        Good good = goodRepository.getById(goodInCart.getGoodId());
        if(cart!=null && good!= null) {
            goodInCart.setBoughtAmount(
                     cart.getBoughtGoods().stream()
                             .filter(g -> g.getGoodId().equals(goodInCart.getGoodId()))
                             .findAny()
                             .orElse(new GoodInCart())
                             .getBoughtAmount() - goodInCart.getBoughtAmount()

            );
            int oldIndex = cart.getBoughtGoods().indexOf(cart.getBoughtGoods().stream().filter(g -> g.getGoodId().equals(goodInCart.getGoodId())).findAny().get());
            if(goodInCart.getBoughtAmount()>0)
                cart.getBoughtGoods().set(oldIndex, goodInCart);
            else
                cart.getBoughtGoods().remove(oldIndex);
            return cartRepository.save(cart);
        }
        else
            throw new Exception();
    }
}
