package rtuitlab.buys.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.models.Category;
import rtuitlab.buys.models.Receipt;
import rtuitlab.buys.repositories.BoughtGoodRepository;
import rtuitlab.buys.repositories.CategoryRepository;
import rtuitlab.buys.repositories.ReceiptRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private BoughtGoodRepository boughtGoodRepository;
    private ReceiptRepository receiptRepository;
    private RestTemplate restTemplate;


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(String id) {
        return categoryRepository.getById(id);
    }

    public List<Category> create(Category category) {
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    public Category update(String id, Category category) {
        category.setId(id);
        categoryRepository.save(category);
        return categoryRepository.getById(id);
    }

    public List<Category> delete(String id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }

    public List<Category> updateCategoriesForGoodFromShop(String token, String goodId) {
        String username = JWT.decode(token.substring(7)).getSubject();
        List<BoughtGood> boughtGoods = new ArrayList<>();
        List<List<BoughtGood>> receiptsBoughtGoods = receiptRepository.getAllByUsername(username).stream().map(garr -> garr.getBoughtGoods().stream().filter(g -> g.getGoodId().equals(goodId)).collect(Collectors.toList())).toList();
        for (List<BoughtGood> boughtGoodList: receiptsBoughtGoods) {
            boughtGoods.addAll(boughtGoodList);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.substring(7));
        ResponseEntity<Category[]> categoriesResponse = restTemplate.exchange("http://shops/api/categories/?good_id=" + goodId, HttpMethod.GET, new HttpEntity<>(headers), Category[].class);
        List<Category> categories = new ArrayList<>(Arrays.asList(Objects.requireNonNull(categoriesResponse.getBody())));

        categoryRepository.saveAll(categories);
        for(BoughtGood boughtGood: boughtGoods) {
            boughtGood.setCategories(categories);
        }
        boughtGoodRepository.saveAll(boughtGoods);
        return categories;
    }
}
