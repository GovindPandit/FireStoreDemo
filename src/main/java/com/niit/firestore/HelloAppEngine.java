package com.niit.firestore;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

@WebServlet(
    name = "LoginController",
    urlPatterns = {"/LoginController"}
)
public class HelloAppEngine extends HttpServlet 
{
	 private Datastore datastore;
	 private KeyFactory keyFactory;
	 
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException 
  {
	  datastore = DatastoreOptions.getDefaultInstance().getService();
	  keyFactory = datastore.newKeyFactory().setKind("users");
	
	  Key taskKey = datastore.add(FullEntity.newBuilder(keyFactory.newKey()).build()).getKey();
	  Entity entity = Entity.newBuilder(taskKey)
	      .set("username", request.getParameter("username"))
	      .set("email", request.getParameter("email"))
	      .set("password", request.getParameter("password"))
	      .build();
	  datastore.put(entity);
	  
  }
}