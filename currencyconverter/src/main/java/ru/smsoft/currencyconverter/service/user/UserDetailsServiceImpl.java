package ru.smsoft.currencyconverter.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.smsoft.currencyconverter.dal.ConversionHistoryRepository;
import ru.smsoft.currencyconverter.dal.UserRepository;
import ru.smsoft.currencyconverter.model.ConversionHistory;
import ru.smsoft.currencyconverter.model.User;
import ru.smsoft.currencyconverter.config.security.UserDecorator;
import ru.smsoft.currencyconverter.config.security.Roles;

import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ConversionHistoryRepository conversionHistoryRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, ConversionHistoryRepository conversionHistoryRepository) {
        this.userRepository = userRepository;
        this.conversionHistoryRepository = conversionHistoryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserLogin(userLogin)
                .orElseThrow(() -> new UsernameNotFoundException("no user with such login"));
        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role == Roles.ADMIN);
        if(isAdmin){
             List<ConversionHistory> conversionHistories = conversionHistoryRepository.findAll();
            for (ConversionHistory conversionHistory : conversionHistories) {
                user.getConversionHistory().add(conversionHistory);
            }
        }
        return new UserDecorator(user);
    }
}
