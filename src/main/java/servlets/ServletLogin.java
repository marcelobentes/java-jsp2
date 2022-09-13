package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

/*O chamado controller são as servlets*/
@WebServlet(urlPatterns = {"/principal/ServletLogin"})/*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletLogin() {
        
        
    }

	/*Recebe os dados por parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/*Recebe os dados enviado por formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			/*simulando o login*/
			if (modelLogin.getLogin().equalsIgnoreCase("admin")
					&& modelLogin.getSenha().equalsIgnoreCase("123")) {
			
			request.getSession().setAttribute("usuario", modelLogin.getLogin());
			
			if(url == null || url.equals("null")) {
				url = "principal/principal.jsp";
			}
			
			RequestDispatcher redirecionar = request.getRequestDispatcher(url);
			redirecionar.forward(request, response);
			
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente !");
				redirecionar.forward(request, response);
			}
		}else {/*redirecionar para o index caso login for invalido*/
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente !");
			redirecionar.forward(request, response);
		}
		
	}

}
