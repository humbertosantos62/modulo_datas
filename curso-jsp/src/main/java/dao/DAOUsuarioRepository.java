package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beandto.BeanDtoGraficoSalarioUser;
import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUsuarioRepository {
	
    private Connection connection;
    
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
		
	}
	
	public BeanDtoGraficoSalarioUser montarGraficoMediaSalario(Long userLogado, String dataInicial, String dataFinal) throws Exception {

		String sql = "select avg(rendamensal) as media_salarial, perfil from model_login where usuario_id  = ? and datanascimento >= ? and datanascimento <= ? group by perfil";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, userLogado);
		preparedStatement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		preparedStatement.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
		
		while (resultSet.next()) {
			Double media_salarial = resultSet.getDouble("media_salarial");
			String perfil = resultSet.getString("perfil");
			
			perfils.add(perfil);
			salarios.add(media_salarial);
		}
		
		beanDtoGraficoSalarioUser.setPerfils(perfils);
		beanDtoGraficoSalarioUser.setSalarios(salarios);
		
		return beanDtoGraficoSalarioUser;
	}
	
        public BeanDtoGraficoSalarioUser montarGraficoMediaSalario(Long userLogado) throws Exception {
		
		String sql = "select avg(rendamensal) as media_salarial, perfil from model_login where usuario_id  = ? group by perfil";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, userLogado);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
		
		while (resultSet.next()) {
			Double media_salarial = resultSet.getDouble("media_salarial");
			String perfil = resultSet.getString("perfil");
			
			perfils.add(perfil);
			salarios.add(media_salarial);
		}
		
		beanDtoGraficoSalarioUser.setPerfils(perfils);
		beanDtoGraficoSalarioUser.setSalarios(salarios);
		
		return beanDtoGraficoSalarioUser;
	}
	
	
    public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception {
		
		if (objeto.isNovo()) {/*Grava um novo*/
		
		String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, codigopostal ,morada,localidade,freguesia, concelho, distrito, datanascimento, rendamensal )  VALUES (?,?, ?, ? ,?, ? , ? ,?, ?, ?, ?, ?, ?, ?,?);";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		preparedSql.setLong(5, userLogado);
		preparedSql.setString(6, objeto.getPerfil());
		preparedSql.setString(7, objeto.getSexo());
		preparedSql.setString(8, objeto.getCodigopostal());
		preparedSql.setString(9, objeto.getMorada());
		preparedSql.setString(10, objeto.getLocalidade());
		preparedSql.setString(11, objeto.getFreguesia());
		preparedSql.setString(12, objeto.getConcelho());
		preparedSql.setString(13, objeto.getDistrito());
		preparedSql.setDate(14, objeto.getDataNascimento());
		preparedSql.setDouble(15, objeto.getRendamensal());
		
		
		preparedSql.execute();
		
		connection.commit();
		
		
			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "update model_login set fotouser =?, extensaofotouser=? where login =?";
				
				preparedSql = connection.prepareStatement(sql);
				
				preparedSql.setString(1, objeto.getFotouser());
				preparedSql.setString(2, objeto.getExtensaofotouser());
				preparedSql.setString(3, objeto.getLogin());
				
				preparedSql.execute();
				
				connection.commit();
			}
		
		}else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, codigopostal=?, morada =?, localidade=?, freguesia=?, concelho=?, distrito =?, datanascimento=?, rendamensal =? WHERE id =  "+objeto.getId()+";";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString(1, objeto.getLogin());
			prepareSql.setString(2, objeto.getSenha());
			prepareSql.setString(3, objeto.getNome());
			prepareSql.setString(4, objeto.getEmail());
			prepareSql.setString(5, objeto.getPerfil());
			prepareSql.setString(6, objeto.getSexo());
			
			prepareSql.setString(7, objeto.getCodigopostal());
			prepareSql.setString(8, objeto.getMorada());
			prepareSql.setString(9, objeto.getLocalidade());
			prepareSql.setString(10, objeto.getFreguesia());
			prepareSql.setString(11, objeto.getConcelho());
			prepareSql.setString(12, objeto.getDistrito());
			prepareSql.setDate(13, objeto.getDataNascimento());
			prepareSql.setDouble(14, objeto.getRendamensal());
			
			prepareSql.executeUpdate();
			
			connection.commit();
			
			
			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "update model_login set fotouser =?, extensaofotouser=? where id =?";
				
				prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString(1, objeto.getFotouser());
				prepareSql.setString(2, objeto.getExtensaofotouser());
				prepareSql.setLong(3, objeto.getId());
				
				prepareSql.execute();
				
				connection.commit();
			}
			
		}
		
		
		return this.consultaUsuario(objeto.getLogin(), userLogado);
	}

   public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws Exception {
  	
  	List<ModelLogin> retorno = new ArrayList<ModelLogin>();
  	
  	String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + "order by nome offset "+offset+" limit 5 ";
  	PreparedStatement statement = connection.prepareStatement(sql);
  	
  	ResultSet resultado = statement.executeQuery();
  	
  	while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
  		
  		ModelLogin modelLogin = new ModelLogin();
  		
  		modelLogin.setEmail(resultado.getString("email"));
  		modelLogin.setId(resultado.getLong("id"));
  		modelLogin.setLogin(resultado.getString("login"));
  		modelLogin.setNome(resultado.getString("nome"));
  		//modelLogin.setSenha(resultado.getString("senha"));
  		modelLogin.setPerfil(resultado.getString("perfil"));
  		modelLogin.setSexo(resultado.getString("sexo"));
  		
  		
  		
  		retorno.add(modelLogin);
  	}
  	
  	
  	return retorno;
  } 

     public int totalPagina(Long userLogado) throws Exception {
		
	String sql = "select count(1) as total from model_login  where usuario_id = " + userLogado;
	
	PreparedStatement statement = connection.prepareStatement(sql);
	
	ResultSet resultado = statement.executeQuery();
	
	resultado.next();
	
	Double cadastros = resultado.getDouble("total");
	
	Double porpagina = 5.0;
	
	Double pagina = cadastros / porpagina;
	
	Double resto = pagina % 2;
	
	if (resto > 0) {
		pagina ++;
	}
	
	return pagina.intValue();
	
}
     public List<ModelLogin> consultaUsuarioListRel(Long userLogado) throws Exception {
 		
 		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
 		
 		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado;
 		PreparedStatement statement = connection.prepareStatement(sql);
 		
 		ResultSet resultado = statement.executeQuery();
 		
 		while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
 			
 			ModelLogin modelLogin = new ModelLogin();
 			
 			modelLogin.setEmail(resultado.getString("email"));
 			modelLogin.setId(resultado.getLong("id"));
 			modelLogin.setLogin(resultado.getString("login"));
 			modelLogin.setNome(resultado.getString("nome"));
 			//modelLogin.setSenha(resultado.getString("senha"));
 			modelLogin.setPerfil(resultado.getString("perfil"));
 			modelLogin.setSexo(resultado.getString("sexo"));
 			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
 			modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
  			modelLogin.setMorada(resultado.getString("Morada"));
  			modelLogin.setLocalidade(resultado.getString("Localidade"));
  			modelLogin.setFreguesia(resultado.getString("Freguesia"));
  			modelLogin.setConcelho(resultado.getString("Concelho"));
  			modelLogin.setDistrito(resultado.getString("Distrito"));
  			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
  			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
 			
 			
 			modelLogin.setTelefones(this.listFone(modelLogin.getId()));
 			
 			retorno.add(modelLogin);
 		}
 		
 		
 		return retorno;
 	}
     
     public List<ModelLogin> consultaUsuarioListRel(Long userLogado, String dataInicial, String dataFinal) throws Exception {
 		
 		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
 		
 		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " and datanascimento >= ? and datanascimento <= ?";
 		PreparedStatement statement = connection.prepareStatement(sql);
 		statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
 		statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
 		
 		ResultSet resultado = statement.executeQuery();
 		
 		while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
 			
 			ModelLogin modelLogin = new ModelLogin();
 			
 			modelLogin.setEmail(resultado.getString("email"));
 			modelLogin.setId(resultado.getLong("id"));
 			modelLogin.setLogin(resultado.getString("login"));
 			modelLogin.setNome(resultado.getString("nome"));
 			//modelLogin.setSenha(resultado.getString("senha"));
 			modelLogin.setPerfil(resultado.getString("perfil"));
 			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
 			modelLogin.setSexo(resultado.getString("sexo"));
 			modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
  			modelLogin.setMorada(resultado.getString("Morada"));
  			modelLogin.setLocalidade(resultado.getString("Localidade"));
  			modelLogin.setFreguesia(resultado.getString("Freguesia"));
  			modelLogin.setConcelho(resultado.getString("Concelho"));
  			modelLogin.setDistrito(resultado.getString("Distrito"));
  			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
  			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
 			
 			modelLogin.setTelefones(this.listFone(modelLogin.getId()));
 			
 			retorno.add(modelLogin);
 		}
 		
 		
 		return retorno;
 	}

    public int consultaUsuarioListTotalPaginaPaginacao(String nome, Long userLogado) throws Exception {
	
	
	
	String sql = "select count(1) as total from model_login  where upper(nome) like upper(?) and useradmin is false and usuario_id = ? ";
	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setString(1, "%" + nome + "%");
	statement.setLong(2, userLogado);
	ResultSet resultado = statement.executeQuery();
	
	resultado.next();
	
	Double cadastros = resultado.getDouble("total");
		
		Double porpagina = 5.0;
		
		Double pagina = cadastros / porpagina;
		
		Double resto = pagina % 2;
		
		if (resto > 0) {
			pagina ++;
		}
		
		return pagina.intValue();
    }
    
  	public List<ModelLogin> consultaUsuarioListOffSet(String nome, Long userLogado, int offset) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login  where upper(nome) like upper(?) and useradmin is false and usuario_id = ? offset "+offset+" limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
		}
		
		
		return retorno;
	}
	
    public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {
    	
    	List<ModelLogin> retorno = new ArrayList<ModelLogin>();
    	
    	String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " limit 5";
    	PreparedStatement statement = connection.prepareStatement(sql);
    	
    	ResultSet resultado = statement.executeQuery();
    	
    	while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
    		
    		ModelLogin modelLogin = new ModelLogin();
    		
    		modelLogin.setEmail(resultado.getString("email"));
    		modelLogin.setId(resultado.getLong("id"));
    		modelLogin.setLogin(resultado.getString("login"));
    		modelLogin.setNome(resultado.getString("nome"));
    		//modelLogin.setSenha(resultado.getString("senha"));
    		modelLogin.setPerfil(resultado.getString("perfil"));
    		modelLogin.setSexo(resultado.getString("sexo"));
    		
    		
    		
    		retorno.add(modelLogin);
    	}
    	
    	
    	return retorno;
    }
        
          public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {
        	
        	List<ModelLogin> retorno = new ArrayList<ModelLogin>();
        	
        	String sql = "select * from model_login  where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";
        	PreparedStatement statement = connection.prepareStatement(sql);
        	statement.setString(1, "%" + nome + "%");
        	statement.setLong(2, userLogado);
        	ResultSet resultado = statement.executeQuery();
        	
        	while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
        		
        		ModelLogin modelLogin = new ModelLogin();
        		
        		modelLogin.setEmail(resultado.getString("email"));
        		modelLogin.setId(resultado.getLong("id"));
        		modelLogin.setLogin(resultado.getString("login"));
        		modelLogin.setNome(resultado.getString("nome"));
        		//modelLogin.setSenha(resultado.getString("senha"));
        		modelLogin.setPerfil(resultado.getString("perfil"));
        		modelLogin.setSexo(resultado.getString("sexo"));
        		
        		
        		retorno.add(modelLogin);
        	}
        	
        	
        	return retorno;
        }
          
       
        public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception  {
    		
    		ModelLogin modelLogin = new ModelLogin();
    		
    		String sql = "select * from model_login where upper(login) = upper('"+login+"')and useradmin is false and usuario_id = " + userLogado ;
    		
    		PreparedStatement statement = connection.prepareStatement(sql);
    		
    		ResultSet resultado =  statement.executeQuery();
    		
    		while (resultado.next()) /*Se tem resultado*/ {
    			
    			modelLogin.setId(resultado.getLong("id"));
    			modelLogin.setEmail(resultado.getString("email"));
    			modelLogin.setLogin(resultado.getString("login"));
    			modelLogin.setSenha(resultado.getString("senha"));
    			modelLogin.setNome(resultado.getString("nome"));
    			modelLogin.setPerfil(resultado.getString("perfil"));
    			modelLogin.setSexo(resultado.getString("sexo"));
    			modelLogin.setFotouser(resultado.getString("fotouser"));
    			modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
      			modelLogin.setMorada(resultado.getString("Morada"));
      			modelLogin.setLocalidade(resultado.getString("Localidade"));
      			modelLogin.setFreguesia(resultado.getString("Freguesia"));
      			modelLogin.setConcelho(resultado.getString("Concelho"));
      			modelLogin.setDistrito(resultado.getString("Distrito"));
      			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
      			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
    			
    		}
    		
    		return modelLogin;
    		
    	}
        
        public ModelLogin consultaUsuario(String login) throws Exception  {
    		
    		ModelLogin modelLogin = new ModelLogin();
    		
    		String sql = "select * from model_login where upper(login) = upper('"+login+"')and useradmin is false" ;
    		
    		PreparedStatement statement = connection.prepareStatement(sql);
    		
    		ResultSet resultado =  statement.executeQuery();
    		
    		while (resultado.next()) /*Se tem resultado*/ {
    			
    			modelLogin.setId(resultado.getLong("id"));
    			modelLogin.setEmail(resultado.getString("email"));
    			modelLogin.setLogin(resultado.getString("login"));
    			modelLogin.setSenha(resultado.getString("senha"));
    			modelLogin.setNome(resultado.getString("nome"));
    			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
    			modelLogin.setPerfil(resultado.getString("perfil"));
    			modelLogin.setSexo(resultado.getString("sexo"));
    			modelLogin.setFotouser(resultado.getString("fotouser"));
    			modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
      			modelLogin.setMorada(resultado.getString("Morada"));
      			modelLogin.setLocalidade(resultado.getString("Localidade"));
      			modelLogin.setFreguesia(resultado.getString("Freguesia"));
      			modelLogin.setConcelho(resultado.getString("Concelho"));
      			modelLogin.setDistrito(resultado.getString("Distrito"));
      			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
      			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
    			
    		}
    		
    		return modelLogin;
    		
    	}
        
        public ModelLogin consultaUsuarioLogado(String login) throws Exception  {
    		
      		ModelLogin modelLogin = new ModelLogin();
      		
      		String sql = "select * from model_login where upper(login) = upper('"+login+"')" ;
      		
      		PreparedStatement statement = connection.prepareStatement(sql);
      		
      		ResultSet resultado =  statement.executeQuery();
      		
      		while (resultado.next()) /*Se tem resultado*/ {
      			
      			modelLogin.setId(resultado.getLong("id"));
      			modelLogin.setEmail(resultado.getString("email"));
      			modelLogin.setLogin(resultado.getString("login"));
      			modelLogin.setSenha(resultado.getString("senha"));
      			modelLogin.setNome(resultado.getString("nome"));
      			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
      			modelLogin.setPerfil(resultado.getString("perfil"));
      			modelLogin.setSexo(resultado.getString("sexo"));
      			modelLogin.setFotouser(resultado.getString("fotouser"));
      			modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
      			modelLogin.setMorada(resultado.getString("Morada"));
      			modelLogin.setLocalidade(resultado.getString("Localidade"));
      			modelLogin.setFreguesia(resultado.getString("Freguesia"));
      			modelLogin.setConcelho(resultado.getString("Concelho"));
      			modelLogin.setDistrito(resultado.getString("Distrito"));
      			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
      			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
      			
      			
      		}
      		
      		return modelLogin;
      		
      	}
        
        
    	public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception  {
    		
    		ModelLogin modelLogin = new ModelLogin();
    		
    		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ? ";
    		
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setLong(1, Long.parseLong(id));
    		statement.setLong(2, userLogado);
    		
    		
    		ResultSet resultado =  statement.executeQuery();
    		
    		while (resultado.next()) /*Se tem resultado*/ {
    			
    			modelLogin.setId(resultado.getLong("id"));
    			modelLogin.setEmail(resultado.getString("email"));
    			modelLogin.setLogin(resultado.getString("login"));
    			modelLogin.setSenha(resultado.getString("senha"));
    			modelLogin.setNome(resultado.getString("nome"));
    			modelLogin.setPerfil(resultado.getString("perfil"));
    			modelLogin.setSexo(resultado.getString("sexo"));
    			modelLogin.setFotouser(resultado.getString("fotouser"));
        		modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
        		modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
      			modelLogin.setMorada(resultado.getString("Morada"));
      			modelLogin.setLocalidade(resultado.getString("Localidade"));
      			modelLogin.setFreguesia(resultado.getString("Freguesia"));
      			modelLogin.setConcelho(resultado.getString("Concelho"));
      			modelLogin.setDistrito(resultado.getString("Distrito"));
      			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
      			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
    			
    			
    		}
    		
    		return modelLogin;
    	}
    	
    	public ModelLogin consultaUsuarioID(Long id) throws Exception  {
    		
    		ModelLogin modelLogin = new ModelLogin();
    		
    		String sql = "select * from model_login where id = ? and useradmin is false ";
    		
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setLong(1, id);
    		
    		ResultSet resultado =  statement.executeQuery();
    		
    		while (resultado.next()) /*Se tem resultado*/ {
    			
    			modelLogin.setId(resultado.getLong("id"));
    			modelLogin.setEmail(resultado.getString("email"));
    			modelLogin.setLogin(resultado.getString("login"));
    			modelLogin.setSenha(resultado.getString("senha"));
    			modelLogin.setNome(resultado.getString("nome"));
    			modelLogin.setPerfil(resultado.getString("perfil"));
    			modelLogin.setSexo(resultado.getString("sexo"));
    			modelLogin.setFotouser(resultado.getString("fotouser"));
        		modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
        		modelLogin.setCodigopostal(resultado.getString("Codigopostal"));
      			modelLogin.setMorada(resultado.getString("Morada"));
      			modelLogin.setLocalidade(resultado.getString("Localidade"));
      			modelLogin.setFreguesia(resultado.getString("Freguesia"));
      			modelLogin.setConcelho(resultado.getString("Concelho"));
      			modelLogin.setDistrito(resultado.getString("Distrito"));
      			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
      			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
    			
    			
    		}
    		
    		return modelLogin;
    	}
 
        
    	public boolean validarLogin(String login) throws Exception {
    		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"');";
    		
            PreparedStatement statement = connection.prepareStatement(sql);
    		
    		ResultSet resultado =  statement.executeQuery();
    		
    		resultado.next();/*Pra ele entrar nos resultados do sql*/
    		return resultado.getBoolean("existe");
    		
    	}
    	
    	public void deletarUser(String idUser) throws Exception {
    		String sql = "DELETE FROM model_login WHERE id = ? and useradmin is false;";
    		
    		PreparedStatement prepareSql = connection.prepareStatement(sql);
    		
    		prepareSql.setLong(1, Long.parseLong(idUser));
    		
    		prepareSql.executeUpdate();
    		
    		connection.commit();
    		
    	}
    	
    	public List<ModelTelefone> listFone(Long idUserPai) throws Exception {
    		
    		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
    		
    		String sql = "select * from telefone where usuario_pai_id = ?";
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		
    		preparedStatement.setLong(1, idUserPai);
    		
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while (rs.next()) {
    			
    			ModelTelefone modelTelefone = new ModelTelefone();
    			
    			modelTelefone.setId(rs.getLong("id"));
    			modelTelefone.setNumero(rs.getString("numero"));
    			modelTelefone.setUsuario_cad_id(this.consultaUsuarioID(rs.getLong("usuario_cad_id")));
    			modelTelefone.setUsuario_pai_id(this.consultaUsuarioID(rs.getLong("usuario_pai_id")));
    			
    			retorno.add(modelTelefone);
    			
    		}
    		
    		return retorno;
    	}

}
