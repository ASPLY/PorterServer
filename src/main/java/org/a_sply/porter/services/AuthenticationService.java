package org.a_sply.porter.services;

import org.a_sply.porter.domain.user.User;

public interface AuthenticationService {
	User getCurrentUser();
}
