import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App{
    public static void main(String[] args){

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int number = Integer.parseInt(request.queryParams("number"));
            int age = Integer.parseInt(request.queryParams("age"));
            String gender = request.queryParams("gender");
            Stylist stylist = new Stylist(name,number,age,gender);
            stylist.save();
            model.put("stylist", stylist);
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));

            String name = request.queryParams("clientname");
            int number = Integer.parseInt(request.queryParams("clientnumber"));
            String gender = request.queryParams("clientgender");

            String email = request.queryParams("email");
            Client client = new Client(name, number, email, 1,gender);
            client.save();
            model.put("stylists", Stylist.all());
            model.put("stylist", stylist);
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
            model.put("stylists", Stylist.all());
//            model.put("stylist", stylist);
            model.put("template", "templates/form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Client.all());
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}

