package com.csci5308.stocki5.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDbMock implements UserDbInterface
{

	@Override
	public boolean insertUser(User user)
	{
		System.out.println(user.getUserCode());
		return true;
	}

	@Override
	public boolean updateUser(User user)
	{
		System.out.println(user.getUserCode());
		return true;
	}

	@Override
	public User getUser(String userCode)
	{
		System.out.println(userCode);
		return null;
	}

	@Override
	public User getUserByEmail(String email)
	{
		System.out.println(email);
		if(email.equals("test@example.com")) {
			User user = new User();
			user.setEmailId(email);
			user.setUserCode("TEST12456789");
			Date dob = null;
			try {
				dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			user.setDateOfBirth(dob);
			return user;
		}
		else {
			return null;
		}
	}

}