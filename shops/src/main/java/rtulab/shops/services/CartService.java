package rtulab.shops.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rtulab.shops.models.mongoDocuments.Cart;
import rtulab.shops.repositories.CartRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;

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

    public Cart update(String id, Cart newCart) {
        return cartRepository.save(newCart);
    }

    public List<Cart> delete(String id) {
        cartRepository.deleteById(id);
        return cartRepository.findAll();
    }
}
