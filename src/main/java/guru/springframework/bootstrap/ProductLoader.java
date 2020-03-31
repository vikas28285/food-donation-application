package guru.springframework.bootstrap;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private Logger log = Logger.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product shirt = new Product();
        shirt.setDescription("Address 1, Address 2, Address 3");
        shirt.setPrice("Rahul Kumar");
        shirt.setImageUrl("2020-04-01T08:30");
        shirt.setProductId("9844357621");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Address 4, Address 5, Address 6");
        mug.setImageUrl("2020-03-31T10:00");
        mug.setProductId("9689087889");
        mug.setPrice("Dinesh Singh");
        productRepository.save(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }
}
