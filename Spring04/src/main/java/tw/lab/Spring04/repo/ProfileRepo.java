package tw.lab.Spring04.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring04.entity.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

}
