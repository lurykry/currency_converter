package ru.smsoft.currencyconverter.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smsoft.currencyconverter.dao.UserRepository;
import ru.smsoft.currencyconverter.dto.UserDto;
import ru.smsoft.currencyconverter.exception.AccountAlreadyExistException;
import ru.smsoft.currencyconverter.exception.PasswordShouldBeBetweenSizAndThirtyTwoCharactersException;
import ru.smsoft.currencyconverter.exception.PasswordsDoNotMatchException;
import ru.smsoft.currencyconverter.model.User;
import ru.smsoft.currencyconverter.security.Roles;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByUserLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
    }

    @Override
    public User registerUser(UserDto userDto) throws AccountAlreadyExistException, PasswordShouldBeBetweenSizAndThirtyTwoCharactersException, PasswordsDoNotMatchException {
        if(emailExists(userDto.getUserEmail())){
            throw new AccountAlreadyExistException("Account with such email already exists");
        }
        if(loginExists(userDto.getUserLogin())){
            throw new AccountAlreadyExistException("Account with such login already exists");
        }
        if(userDto.getUserPassword().length() < 6 ||
                userDto.getUserPassword().length() > 32){
            throw new PasswordShouldBeBetweenSizAndThirtyTwoCharactersException("Password should be between" +
                    " 6 and 32 characters long");
        }
        if(!userDto.getUserPassword().equals(userDto.getMatchingPassword())){
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }
        User user = new User();
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(encoder.encode(userDto.getUserPassword()));
        user.setUserLogin(userDto.getUserLogin());
        user.getRoles().add(Roles.USER);

        userRepository.save(user);
        return user;
    }

    private boolean emailExists(String email){
        return userRepository.findUserByUserEmail(email).isPresent();
    }
    private boolean loginExists(String login){
        return userRepository.findUserByUserLogin(login).isPresent();
    }
}
