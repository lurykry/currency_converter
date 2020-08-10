package ru.smsoft.currencyconverter.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smsoft.currencyconverter.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUserLogin(String userName);
    Optional<User> findUserByUserEmail(String userEmail);

//    @Modifying
//    @Query("UPDATE User u SET u.conversionHistory = 1 where u.userId = 2")
//    void updateUserHistory(ConversionHistory conversionHistory, Long userId);
}
