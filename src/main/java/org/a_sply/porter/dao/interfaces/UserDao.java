package org.a_sply.porter.dao.interfaces;

import java.util.List;

import org.a_sply.porter.domain.user.User;
import org.a_sply.porter.domain.user.UserCondition;

public interface UserDao {

	long insert(User user);
	User selectByUserId(SelectType selectType, long userId);
	List<User> selectByCondition(SelectType selectType, UserCondition userCondition);
}
