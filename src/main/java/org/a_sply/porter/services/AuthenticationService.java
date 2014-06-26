package org.a_sply.porter.services;

import org.a_sply.porter.domain.User;

public interface AuthenticationService {
	User getCurrentUser();
}
