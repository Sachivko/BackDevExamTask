package com.stafffinder.task.commonapi;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import com.stafffinder.task.exception.UserRegistrationExeption;
import com.stafffinder.task.model.User;
import com.stafffinder.task.utils.HibernateUtil;
import com.stafffinder.task.utils.UserMail;

@Path("/api")
public class UserController {
	@POST
	@Path("/registerUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(String user) throws UserRegistrationExeption, SQLException {
		User currentUser = new User();
		try {
			JSONObject userJSON = new JSONObject(user);

			if (!betweenExclusive(((String) userJSON.get("firstName")).length(), 2, 50)
					|| !betweenExclusive(((String) userJSON.get("lastName")).length(), 2, 50)) {
				throw new UserRegistrationExeption("First and last name must contain 2­50 characters.");
			}

			if (!((String) userJSON.get("firstName")).matches(".*[A-Z].*")
					|| !((String) userJSON.get("firstName")).matches(".*[a-z].*")
					|| !((String) userJSON.get("lastName")).matches(".*[A-Z].*")
					|| !((String) userJSON.get("lastName")).matches(".*[a-z].*")) {
				throw new UserRegistrationExeption(
						"First and last name must consist of both small and capital letters.");
			}

			if (userJSON.get("landlinePhoneNumber").toString().isEmpty()
					&& userJSON.get("mobilePhoneNumber").toString().isEmpty()) {
				throw new UserRegistrationExeption(
						"Landline phone number or mobile phone number must be provided, or both.");
			}

			currentUser.setEmail((String) userJSON.get("email"));
			currentUser.setFirstName((String) userJSON.get("firstName"));
			currentUser.setLastName((String) userJSON.get("lastName"));
			currentUser.setPassword((String) userJSON.get("password"));
			currentUser.setLandlinePhoneNumber((String) userJSON.get("landlinePhoneNumber"));
			currentUser.setMobilePhoneNumber((String) userJSON.get("mobilePhoneNumber"));

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(currentUser);
			session.getTransaction().commit();
			session.close();

			UserMail.sendEmail(currentUser);

		} catch (MessagingException | JSONException e) {
			throw new UserRegistrationExeption("Error while user registration:", e);
		}
		return Response.status(200).entity(currentUser.toString()).build();
	}

	public static boolean betweenExclusive(int x, int min, int max) {
		return x >= min && x <= max;
	}

}