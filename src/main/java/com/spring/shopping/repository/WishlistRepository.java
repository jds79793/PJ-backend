package com.spring.shopping.repository;

import com.spring.shopping.entity.Product;
import com.spring.shopping.entity.Wishlist;
import com.spring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser(User user);

    Optional<Wishlist> findByUserAndProduct(User user, Product product);
}
