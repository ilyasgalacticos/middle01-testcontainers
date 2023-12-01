package kz.bitlab.middle01.middle01testcontainers.repository;

import kz.bitlab.middle01.middle01testcontainers.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}
