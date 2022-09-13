package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterAutenticacao
 */
@WebFilter(urlPatterns = {"/principal/*"})//intercepetas todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao extends HttpFilter implements Filter{
       
    
	private static final long serialVersionUID = 1L;

	
    public FilterAutenticacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*Encerra os processos qundo o servidor é parado*/
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/*Intercepta as requisições e as respostas no sistema*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		String urlParaAutenticar = req.getServletPath();/*url que esta sendo acessada*/
		
		/*Validar se está logado senão redireciona para a tela de login*/
		
		if (usuarioLogado == null  && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/*usuario não esta logado.*/
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redireciona.forward(request, response);
			return; /*para a execução e redireciona para o login*/
		}else {
			chain.doFilter(request, response);
			
		}
		
		
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
