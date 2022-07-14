package com.google.sps.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/messages")
public class NewFriendServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Message").setOrderBy(OrderBy.desc("timestamp"))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        response.setContentType("text/html");
        while (results.hasNext()) {
            Entity entity = results.next();

            long id = entity.getKey().getId();
            String name = entity.getString("name");
            String message = entity.getString("text-input");
            String email = entity.getString("email");
            String utcTime = entity.getString("UTCtime");
            long timestamp = entity.getLong("timestamp");

            response.getWriter().write("<p> id: " + id + "  Name: " + name + "  Message: " + message + "  Email: "
                    + email + "  UTCtime: " + utcTime + "  TimeStamp" + timestamp + "</p>");
            response.getWriter().write("<br/>");
        }

    }
}