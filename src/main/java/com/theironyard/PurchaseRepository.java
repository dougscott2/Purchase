package com.theironyard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by DrScott on 11/11/15.
 */
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
   Page<Purchase> findByCategory(Pageable pageable, String category);




}
