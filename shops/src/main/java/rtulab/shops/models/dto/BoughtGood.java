package rtulab.shops.models.dto;

import rtulab.shops.models.mongoDocuments.Category;

import java.util.List;

public class BoughtGood {
    private String id;
    private String name;
    private Integer cost;
    private List<Category> categories;
}
