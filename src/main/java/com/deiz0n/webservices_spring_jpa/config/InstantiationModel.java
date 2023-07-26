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

        var c1 = new Category("Eletrônicos");
        var c2 = new Category("Moda");
        var c3 = new Category("Livros");
        var c4 = new Category("Esportes");
        var c5 = new Category("Decoração");

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        var p1 = new Product("SmartPhone Avançado", "Smartphone avançado com tela Amoled", 499.99, "https://example.com/img/smartphone.jpg");
        var p2 = new Product("Fones de ouvido sem fio", "Fones de ouvido sem fio com cancelamento de ruído", 149.50, "https://example.com/img/fones.jpg");
        var p3 = new Product("Camiseta Estampada", "Camiseta de algodão com estampa moderna", 27.99, "https://example.com/img/camiseta.jpg");
        var p4 = new Product("Bola de Futebol FIFA", "Bola de futebol oficial da FIFA", 69.75, "https://example.com/img/bola.jpg");
        var p5 = new Product("Vaso de Cerâmica", "Vaso de cerâmica para plantas", 89.99,"https://example.com/img/vaso.jpg");

        p1.getCategories().add(c1);
        p2.getCategories().add(c1);
        p3.getCategories().add(c3);
        p4.getCategories().add(c4);
        p5.getCategories().add(c5);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        var u1 = new User("User 1", "user1@example.com", "Rua das Flores, 123", "(11)98765-4321", null);
        u1.setPassword("senha123");
        var u2 = new User("User 2", "user2@example.com", "Avenida dos Sonhos, 456", "(22)99888-7766", null);
        u2.setPassword("senha456");
        var u3 = new User("User 3", "user3@example.com", "Praça das Estrelas, 789", "(33)97777-5555", null);
        u3.setPassword("senha789");
        var u4 = new User("User 4", "user4@example.com", "Avenida das Maravilhas, 1010", "(44)96666-4444", null);
        u4.setPassword("senha1010");
        var u5 = new User("User 5", "user5@example.com", "Rua dos Milagres, 222", "(55)95555-3333", null);
        u5.setPassword("senha222");

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));

        var o1 = new Order(Instant.parse("2023-07-25T12:34:56.789123-07:00"), OrderStatus.WAITING_PAYMENT, u1);
        var o2 = new Order(Instant.parse("2023-07-24T18:20:45.678123-07:00"), OrderStatus.PAID, u2);
        var o3 = new Order(Instant.parse("2023-07-23T09:15:30.567123-07:00"), OrderStatus.SHIPPED, u3);
        var o4 = new Order(Instant.parse("2023-07-22T15:40:12.456123-07:00"), OrderStatus.DELIVERED, u4);
        var o5 = new Order(Instant.parse("2023-07-21T11:25:55.345123-07:00"), OrderStatus.CANCELED, u5);

        var pay1 = new Payment(Instant.parse("2023-07-25T12:34:56.789123-07:00"), o1);
        var pay2 = new Payment(Instant.parse("2023-07-24T18:20:45.678123-07:00"), o2);
        var pay3 = new Payment(Instant.parse("2023-07-23T09:15:30.567123-07:00"), o3);
        var pay4 = new Payment(Instant.parse("2023-07-22T15:40:12.456123-07:00"), o4);
        var pay5 = new Payment(Instant.parse("2023-07-21T11:25:55.345123-07:00"), o5);

        o1.setPayment(pay1);
        o2.setPayment(pay2);
        o3.setPayment(pay3);
        o4.setPayment(pay4);
        o5.setPayment(pay5);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5));

        var oi1 = new OrderItem(o1, p1, 2, 99.99);
        var oi2 = new OrderItem(o2, p2, 1, 49.95);
        var oi3 = new OrderItem(o3, p3, 3, 34.50);
        var oi4 = new OrderItem(o4, p4, 2, 75.25);
        var oi5 = new OrderItem(o5, p5, 4, 29.99);

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4, oi5));

    }
}
