package ua.com.codefire.ecommerce.data.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.SQLWarningException;
import ua.com.codefire.ecommerce.data.entity.User;
import ua.com.codefire.ecommerce.data.jpa_repo.UserRepo;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ankys on 11.02.2017.
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final Logger LOGGER = Logger.getLogger(UserRepo.class.getName());

    public User create(User objToCreate) {
        try {
            objToCreate.setPassword(DigestUtils.md5Hex(objToCreate.getPassword()));
            return userRepo.saveAndFlush(objToCreate);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (EntityExistsException ex) {
            LOGGER.log(Level.SEVERE, "Such user already exists.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while creating new user.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return null;
    }

    public User read(Integer idToFind) {
        return userRepo.findOne(idToFind);
    }

    public Boolean delete(Integer objToDeleteId) {
        try {
            userRepo.delete(objToDeleteId);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No user found by such id.", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while deleting user.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception, while deleting user.", ex);
        }
        return false;
    }

    public Boolean update(User objToCreate) {
        try {
            userRepo.saveAndFlush(objToCreate);
            return true;
        } catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "No such user found.", ex);
        } catch (SQLWarningException ex) {
            LOGGER.log(Level.SEVERE, "Spring-specific exception", ex);
        } catch (PersistenceException ex){
            LOGGER.log(Level.SEVERE, "Problems with database, while updating user.", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected exception", ex);
        }
        return false;
    }

    public List<User> getAllEntities() {
        return userRepo.findAll();
    }

    public Long getAmountOfEntities() {
        return userRepo.count();
    }

    public User getUserByName(String name) {
        return userRepo.findByUsername(name);
    }

    public boolean ifUserRegistered(String name, String password) {
        if (name == null) {
            return false;
        }

        User userByName = getUserByName(name);

        if (userByName == null) {
            return false;
        }

        if (password == null) {
            return false;
        }

        return userByName.checkPassword(password);
    }

    public List<User> getUsersByPage(int pageNumber, int amountByPage) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageNumber + amountByPage - 1);
        return userRepo.findAll(pageable).getContent();
    }
}
