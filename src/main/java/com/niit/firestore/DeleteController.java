package com.niit.firestore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

@WebServlet("DeleteController")
public class DeleteController extends HttpServlet
{
	private Datastore datastore;
	private KeyFactory keyFactory;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		datastore = DatastoreOptions.getDefaultInstance().getService();
		keyFactory = datastore.newKeyFactory().setKind("users");
		
		Key key=keyFactory.newKey(req.getParameter("key"));
		datastore.delete(key);
		resp.sendRedirect("/DisplayUsers");
	}
}
