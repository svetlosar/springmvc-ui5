package com.github.springui5.model;

import com.github.springui5.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model for {@code home.view.js} view. Will be automatically serialized to JSON via default {@linkplain
 * org.springframework.http.converter.HttpMessageConverter} configured by {@linkplain
 * org.springframework.web.servlet.config.annotation.EnableWebMvc} annotation on {@linkplain
 * com.github.springui5.conf.WebAppConfigurer} configuration class.
 */
public class HomeModel implements Serializable {

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 4558009768124941627L;

	private static final Logger logger = LoggerFactory.getLogger(HomeModel.class);

    private List<User> listOfUsers;

    private String error;

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HomeModel() {
    	listOfUsers = new ArrayList<>(Arrays.asList(new User("John", "Smith", "john.smith@gmail.com", "user", "123"), new User("William", "Shakespeare", "william.shakespeare@gmail.com", "admin", "123")));
    }

    public HomeModel add(User user) {
        // set id, it is 0 after deserializing from JSON
    	user.setId(User.newId());
        listOfUsers.add(user);
        return this;
    }

    public HomeModel delete(final long id) {
        CollectionUtils.filter(listOfUsers, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((User) object).getId() != id;
            }
        });
        return this;
    }

    public HomeModel update(final User user) {
        // find the user with the same id
        User oldUser = (User) CollectionUtils.find(listOfUsers, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return ((User) object).getId() == user.getId();
            }
        });
        // update the user
        oldUser.setName(user.getName());
        return this;
    }

    public HomeModel storeError(String error) {
        this.error = error;
        return this;
    }

    public HomeModel clearError() {
        this.error = null;
        return this;
    }

    public HomeModel show() {
        logger.debug(Arrays.toString(listOfUsers.toArray()));
        return this;
    }

}
