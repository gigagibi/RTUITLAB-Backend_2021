package rtulab.shops.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rtulab.shops.exceptions.BuysServiceInternalErrorException;
import rtulab.shops.exceptions.BuysServiceNotFoundException;
import rtulab.shops.models.dto.buysService.BoughtGood;
import rtulab.shops.models.dto.buysService.GoodInCart;
import rtulab.shops.models.dto.buysService.Receipt;
import rtulab.shops.models.mongoDocuments.Cart;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.repositories.CartRepository;
import rtulab.shops.repositories.CategoryRepository;
import rtulab.shops.repositories.GoodRepository;
import rtulab.shops.exceptions.TooManyBoughtGoodsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private GoodRepository goodRepository;
    private CategoryRepository categoryRepository;
    private RestTemplate restTemplate;

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

    public Cart getByToken(String token, String shopId) {
        String username = JWT.decode(token.substring(7)).getSubject();
        return cartRepository.getByUsernameAndShopId(username, shopId);
    }

    public Cart update(String id, Cart newCart) {
        newCart.setId(id);
        return cartRepository.save(newCart);
    }

    public List<Cart> delete(String id) {
        cartRepository.deleteById(id);
        return cartRepository.findAll();
    }

    public Cart putGoodInCart(String token, String shopId, GoodInCart goodInCart) throws TooManyBoughtGoodsException {
        String username = JWT.decode(token.substring(7)).getSubject();
        Cart cart = cartRepository.getByUsernameAndShopId(username, shopId);
        Good good = goodRepository.getById(goodInCart.getGoodId());
        if(cart!=null) { //if cart exists
            if(cart.getGoodInCarts().stream().anyMatch(g -> g.getGoodId().equals(goodInCart.getGoodId()))) { //if good is already in cart, increase amount of it and replace good in cart
                goodInCart.setBoughtAmount(
                        goodInCart.getBoughtAmount() + cart.getGoodInCarts()
                        .stream()
                        .filter(g -> g.getGoodId().equals(goodInCart.getGoodId()))
                        .findAny()
                        .orElse(new GoodInCart())
                        .getBoughtAmount()
                );
                int oldIndex = cart.getGoodInCarts().indexOf(cart.getGoodInCarts().stream().filter(g -> g.getGoodId().equals(goodInCart.getGoodId())).findAny().get());
                cart.getGoodInCarts().set(oldIndex, goodInCart);
            }
            else {
                cart.getGoodInCarts().add(goodInCart);
            }
        }
        else { //if user doesn't have a cart, creates it and put good
            cart = new Cart();
            cart.setId(Integer.toString(username.toLowerCase().hashCode()));
            cart.setUsername(username);
            cart.setShopId(shopId);
            cart.setGoodInCarts(new ArrayList<>());
            cart.getGoodInCarts().add(goodInCart);
        }
        if(good.getAmount() >= goodInCart.getBoughtAmount()) { //in 3rd case, when user have a cart but there is no good that he's trying to add, its just put good in cart
            return cartRepository.save(cart);
        }
        else
            throw new TooManyBoughtGoodsException();
    }

    public Cart removeGoodFromCart(String token, String shopId, GoodInCart goodInCart) throws Exception {
        String username = JWT.decode(token.substring(7)).getSubject();
        Cart cart = cartRepository.getByUsernameAndShopId(username, shopId);
        Good good = goodRepository.getById(goodInCart.getGoodId());
        if(cart!=null && good!= null) {
            goodInCart.setBoughtAmount(
                     cart.getGoodInCarts().stream()
                             .filter(g -> g.getGoodId().equals(goodInCart.getGoodId()))
                             .findAny()
                             .orElse(new GoodInCart())
                             .getBoughtAmount() - goodInCart.getBoughtAmount()

            );
            int oldIndex = cart.getGoodInCarts().indexOf(cart.getGoodInCarts().stream().filter(g -> g.getGoodId().equals(goodInCart.getGoodId())).findAny().get());
            if(goodInCart.getBoughtAmount()>0)
                cart.getGoodInCarts().set(oldIndex, goodInCart);
            else
                cart.getGoodInCarts().remove(oldIndex);
            return cartRepository.save(cart);
        }
        else
            throw new Exception();
    }

    public String buyAllFromCart(String token, String shopId, String paymentMethod) throws Exception {
        String username = JWT.decode(token.substring(7)).getSubject();
        Cart cart = cartRepository.getByUsernameAndShopId(username, shopId);
        ArrayList<BoughtGood> boughtGoods = new ArrayList<>();
        List<GoodInCart> goodInCarts = cart.getGoodInCarts();
        for (GoodInCart goodInCart: goodInCarts) {
            Good good = goodRepository.getById(goodInCart.getGoodId());
            boughtGoods.add(
                    new BoughtGood(good.getName(),
                            good.getId(),
                            good.getCost(),
                            goodInCart.getBoughtAmount(),
                            good.getCategoriesIds().stream().map(i -> categoryRepository.getById(i)).collect(Collectors.toList())));
            good.setAmount(good.getAmount()-goodInCart.getBoughtAmount());
            goodRepository.save(good);
        }
        Receipt receipt = new Receipt(username, paymentMethod, shopId, boughtGoods);
        cart.getGoodInCarts().clear();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token.substring(7));
        HttpEntity<Receipt> request = new HttpEntity<>(receipt, headers);
        ResponseEntity<Receipt[]> receiptResponseEntity = restTemplate.postForEntity("http://buys/api/receipts/my", request, Receipt[].class);
        if(receiptResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new BuysServiceNotFoundException();
        else if(receiptResponseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
            throw new BuysServiceInternalErrorException();
        else return receiptResponseEntity.toString();
    }}
