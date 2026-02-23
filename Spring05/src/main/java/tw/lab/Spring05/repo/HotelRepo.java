package tw.lab.Spring05.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring05.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Long> {

}
