package com.restapi.osahaneat.responsitory;

import com.restapi.osahaneat.entity.Restaurent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurentRepository extends JpaRepository<Restaurent,Integer> {

}
