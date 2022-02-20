package rtulab.shops.controllers;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtulab.shops.models.dto.GoodInCart;
import rtulab.shops.models.mongoDocuments.Cart;
import rtulab.shops.services.CartService;
import rtulab.shops.services.GoodService;
import rtulab.shops.services.exceptions.TooManyBoughtGoodsException;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartController {
    private CartService cartService;
    private GoodService goodService;

    @GetMapping("/admin")
    public List<Cart> getCarts() {
        return cartService.getAll();
    }

    @GetMapping("/admin/{cart_id}")
    public Cart getCart(@PathVariable(name = "cart_id") String cartId) {
        return cartService.get(cartId);
    }

    @PostMapping("/admin")
    public List<Cart> createCart(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @DeleteMapping("/admin/{cart_id}")
    public List<Cart> deleteCart(@PathVariable(name = "cart_id") String cartId) {
        return cartService.delete(cartId);
    }

    @PutMapping("/admin/{cart_id}")
    public Cart createCart(@PathVariable(name = "cart_id") String cartId, @RequestBody Cart cart) {
        return cartService.update(cartId, cart);
    }

    @GetMapping("/my_cart")
    public Cart getUsersCart(@RequestHeader("Authorization") String token) {
        return cartService.getByToken(token);
    }

    @PostMapping("/my_cart")
    public Cart putGoodInCart(@RequestHeader("Authorization") String token, @RequestBody GoodInCart goodInCart) {
        try {
            return cartService.putGoodInCart(token, goodInCart);
        } catch (TooManyBoughtGoodsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too many goods");
        }
    }

    @DeleteMapping("/my_cart")
    public Cart removeGoodFromCart(@RequestHeader("Authorization") String token, @RequestBody GoodInCart goodInCart) {
        try {
            return cartService.removeGoodFromCart(token, goodInCart);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No good found in cart");
        }
    }

    @GetMapping("/my_cart/buy")
    public String buyAllFromCart(@RequestHeader("Authorization") String token, @RequestParam(name = "payment_method") String paymentMethod) {
        return cartService.buyAllFromCart(token, paymentMethod);
    }

}
