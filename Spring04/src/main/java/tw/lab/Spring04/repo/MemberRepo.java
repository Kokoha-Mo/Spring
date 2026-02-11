package tw.lab.Spring04.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring04.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {
    /*
     * 動詞+By+屬性名稱
     * findByEmail(String email) => Optional<Member>
     * findByAge(Interger age) => List<Member>
     * 
     * countByAge(Interger age) => long
     * 
     * deleteByAge(Integer age)
     * 
     * And/Or
     * findByNameAndAge(String naem,Integer age) =>
     * findByNameOrAge(String naem,Integer age) =>
     * 
     * Between/LessThan/GreateThanEqual
     * findByAgeBetween(Integer min,Integer max)
     * findByOrderDateBetween(Date/LocalDate start,Date/LocalDate end)
     * 
     * IsNull/IsNotNull
     * 
     * OrderBy + 屬性名稱 + (Asc/Desc)
     * findByNameOrderByFirstNameAscAndTitleDesc(String lastname)
     */
}
