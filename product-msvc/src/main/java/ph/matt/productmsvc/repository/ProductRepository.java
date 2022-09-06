package ph.matt.productmsvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ph.matt.productmsvc.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}