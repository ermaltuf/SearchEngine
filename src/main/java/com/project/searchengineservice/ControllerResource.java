package com.project.searchengineservice;

import com.project.utils.IndexReqParser;
import com.project.utils.QueryReqParser;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/main-controller")
public class ControllerResource {

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("id") int id) {
        return Response.ok().entity("{'value': "+ id +"}")
                .build();
    }

    @POST
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getResultSearch(String id ) {
        JSONObject json = new JSONObject(id);
        String command = json.getString("command");
        String result = QueryReqParser.commandSearchParser(command);
        //String nameCommand = json.getString("command");

        return Response.ok().entity(result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @POST
    @Path("/index")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postIndexCommand(String id) {
        JSONObject json = new JSONObject(id);
        String command = json.getString("command");

        String result = IndexReqParser.commandParser(command);

        return Response.ok().entity(result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

}