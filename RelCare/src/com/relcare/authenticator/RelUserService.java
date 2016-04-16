package com.relcare.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.relcare.db.RelcareDao;

/**
 * @author dvarik service to fetch the user from the DB
 *
 */
@Service("relUserService")
public class RelUserService implements UserDetailsService {

	@Autowired
	private RelcareDao dao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;
		try {
			user = dao.loadUser(userName);

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return user;
	}

}
