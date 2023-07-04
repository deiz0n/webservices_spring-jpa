package com.deiz0n.webservices_spring_jpa.config;

import com.deiz0n.webservices_spring_jpa.models.*;
import com.deiz0n.webservices_spring_jpa.models.enums.OrderStatus;
import com.deiz0n.webservices_spring_jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.ClientInfoStatus;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("dev")
public class InstantiationModel implements CommandLineRunner {


    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public InstantiationModel(CategoryRepository categoryRepository, OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, OrderItemRepository orderItemRepository) {
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        /*

        var c1 = new Category("Electronics");
        var c2 = new Category("Books");
        var c3 = new Category("Computers");

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

        var p1 = new Product("MacBook Air", "Notebook da Apple", 1000.1, null);
        var p2 = new Product("Smart TV", "TV com IA e WiFI", 1499.9, null);
        var p3 = new Product("The Witcher", "Livrinho mó bom", 89.9, null);

        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        p1.getCategories().add(c3);
        p2.getCategories().add(c1);
        p2.getCategories().add(c3);
        p3.getCategories().add(c2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        var u1 = new User("Eduardo Silva", "eduardo@gmail.com", "Cedro-CE", "88123456789", "abcde");
        var u2 = new User("Dudu", "dudu@gmail.com", "Icó-CE", "85987654321", "edcba");

        userRepository.saveAll(Arrays.asList(u1, u2));

        var o1 = new Order(Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        var o2 = new Order(Instant.now(), OrderStatus.DELIVERED, u1);
        var o3 = new Order(Instant.parse("2023-06-20T19:53:07Z"), OrderStatus.CANCELED, u2);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        var oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        var oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        var oi3 = new OrderItem(o2, p3, 2, p3.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

        var pay1 = new Payment(Instant.parse("2015-12-23T19:53:07Z"), o2);
        var pay2 = new Payment(Instant.now(), o1);
        var pay3 = new Payment(Instant.parse("2013-01-18T00:53:07Z"), o3);

        o1.setPayment(pay2);
        o2.setPayment(pay1);
        o3.setPayment(pay3);

     */

    }
}
