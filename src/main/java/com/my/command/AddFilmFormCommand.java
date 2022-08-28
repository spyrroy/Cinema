package command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddProductFormCommand extends Command {
    @Override
    public String doCommand(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/addProductForm.jsp";
//        return "redirect:app?cmd=nameOfCommand";
    }
}
