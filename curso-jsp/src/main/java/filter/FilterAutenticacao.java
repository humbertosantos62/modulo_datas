package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connection.SingleConnectionBanco;
import dao.DaoVersionadorBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {//Interceptar todas as requisições que vierem do projeto ou mapeamento
	
	private static Connection connection;
  
    public FilterAutenticacao() {
        
    }

  //Encerra os procesos quando o servidor é parado
    //Mataria os processos ou conexões
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	//Intercepta as requisições e as respostas no servidor
		//Faz tudo no sistema
		//Validação de autenticação
		//Dar commit e roolback de transações no banco
		//validar e fazer redirecionamento de páginas
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
        String urlParaAutenticar = req.getServletPath();/*Url que est� sendo acessada*/
		
		/*Validar se está logado senão redireciona para a tela de login*/
		if (usuarioLogado == null  && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);
			return; /*Para a execução e redireciona para o login*/
		}else {
			
			chain.doFilter(request, response);
		}
		
		connection.commit();
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	//Inicia os processos ou recursos quando o servidor sobre o projeto
	//Iniciar a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection(); 
		
        DaoVersionadorBanco daoVersionadorBanco = new DaoVersionadorBanco();
		
		String caminhoPastaSQL = fConfig.getServletContext().getRealPath("versionadobancosql") + File.separator;
		
		File[] filesSql =  new File(caminhoPastaSQL).listFiles();
		
		try {
			
			
			for (File file : filesSql) {
				
				boolean arquivoJaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());
				
				if (!arquivoJaRodado) {
					
					FileInputStream entradaArquivo = new FileInputStream(file);
					
					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
					
					StringBuilder sql = new StringBuilder();
					
					while (lerArquivo.hasNext()) {
						
						 sql.append(lerArquivo.nextLine());
						 sql.append("\n");
					}
					
					connection.prepareStatement(sql.toString()).execute();
					daoVersionadorBanco.gravaArquivoSqlRodado(file.getName());
					
					connection.commit();
					lerArquivo.close();
				}
			}
		
		
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
