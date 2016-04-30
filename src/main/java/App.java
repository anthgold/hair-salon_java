import java.util.Map;
import java.util.HashMap;
<<<<<<< HEAD
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

=======
import java.util.ArrayList; // removeable as we are no longer using ArrayList
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
>>>>>>> b426a37e37afee10dd82a625ed7f9987786b0d17
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
<<<<<<< HEAD
      Map<String, Object> model = new HashMap<String, Object>();

=======
      HashMap<String, Object> model = new HashMap<String, Object>();
>>>>>>> b426a37e37afee10dd82a625ed7f9987786b0d17
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

<<<<<<< HEAD
=======
    get("tasks/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/task-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("tasks", Task.all());
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");
      Task newTask = new Task(description);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params(":id")));
      model.put("task", task);
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

>>>>>>> b426a37e37afee10dd82a625ed7f9987786b0d17
  }
}
