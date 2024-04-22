package com.kalyp.buildingblocks.archunit.model.persistence;

import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeStore extends JpaRepository {

    @Query
    List<Integer> fetchByHeartRateHigherThan(int rate);

    @Modifying
    void writeHeartRate(int rate);

    List<Integer> queryByHeartRate(int rate);

    List<Integer> readByHeartRate(int rate);

    List<Integer> searchByHeartRate(int rate);

    Stream<Integer> streamByHeartRate(int rate);
}
