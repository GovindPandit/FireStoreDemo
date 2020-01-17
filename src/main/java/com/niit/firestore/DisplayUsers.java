package com.niit.firestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

@WebServlet("/DisplayUsers")
public class DisplayUsers extends HttpServlet
{
	 private Datastore datastore;
	 private KeyFactory keyFactory;
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		 datastore = DatastoreOptions.getDefaultInstance().getService();
		 keyFactory = datastore.newKeyFactory().setKind("users");
		
		 List<User> users=new ArrayList<>();
		 	
		 Query<Entity> query = Query.newEntityQueryBuilder().setKind("users").build();
		 QueryResults<Entity> tasks = datastore.run(query);
		 while(tasks.hasNext())
		 {
			 Entity entity=tasks.next();
			 User u=new User();
			 u.setUserkey(entity.getKey().getId()+"");
			 u.setUsername(entity.getString("username"));
			 u.setEmail(entity.getString("email"));
			 u.setPassword(entity.getString("password"));
			 
			 users.add(u);
		 }
		 
		 HttpSession hs=req.getSession();
		 hs.setAttribute("users", users);
		 
		 resp.sendRedirect("users.jsp");
	}
}
