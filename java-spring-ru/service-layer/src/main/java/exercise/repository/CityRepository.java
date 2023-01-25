package exercise.repository;

import exercise.model.City;
import org.hibernate.annotations.SortNatural;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    List<City> findAll();

    @SortNatural()
    List<City> findByNameStartingWithIgnoreCase(String name);

    Optional<City> findById(Long id);
    // END
}
