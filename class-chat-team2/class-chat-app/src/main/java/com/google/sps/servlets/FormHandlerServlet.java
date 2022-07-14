package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the value entered in the form.

        // Write the value to the response so the user can see it.
        // response.getWriter().println("You submitted: " + textValue);
        // show the alert to the response so the user can see it, redirect to the page

        // String name = request.getParameter("name-input");
        // String message = request.getParameter("text-input");
        // String email = request.getParameter("email-input");

        // Get the value entered in the form.
        String name = Jsoup.clean(request.getParameter("name-input"), Safelist.none());
        String textValue = Jsoup.clean(request.getParameter("text-input"), Safelist.none());
        String email = Jsoup.clean(request.getParameter("email-input"), Safelist.none());
        long timeStamp = System.currentTimeMillis();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Save data in Datastore
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
        FullEntity messageEntity = Entity.newBuilder(keyFactory.newKey())
                .set("name", name)
                .set("timestamp", timeStamp)
                .set("text-input", textValue)
                .set("email", email)
                .set("UTCtime", dateFormat.format(date))
                .build();
        datastore.put(messageEntity);

        // Show in server logs.
        System.out.println("New User submit");
        System.out.println("user submitted: " + textValue);
        System.out.println("user name: " + name);
        System.out.println("user email: " + email);
        System.out.println("User Submition Ends");

        // show the alert then the user knows she/he has submmited the form successfully
        response.getWriter()
                .println("<script> alert('Submission Success!'); window.location.href='/afterprelude.html' </script>");
    }
}