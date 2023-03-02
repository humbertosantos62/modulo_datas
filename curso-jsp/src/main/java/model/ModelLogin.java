package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	private boolean useradmin;
	
	private String perfil;
	
	private String sexo;
	
	private String fotouser;
	
	private String extensaofotouser;
	
	private String codigopostal;
	
	private String morada;
	
	private String localidade;
	
	private String freguesia;
	
	private String concelho;
	
	private String distrito;
	
	private Date dataNascimento; 
	
	private Double rendamensal;
	
	private List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
	
	
	public void setTelefones(List<ModelTelefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<ModelTelefone> getTelefones() {
		return telefones;
	}
	
	public Double getRendamensal() {
		return rendamensal;
	}

	public void setRendamensal(Double rendamensal) {
		this.rendamensal = rendamensal;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getFreguesia() {
		return freguesia;
	}

	public void setFreguesia(String freguesia) {
		this.freguesia = freguesia;
	}

	public String getConcelho() {
		return concelho;
	}

	public void setConcelho(String concelho) {
		this.concelho = concelho;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getFotouser() {
		return fotouser;
	}

	public void setFotouser(String fotouser) {
		this.fotouser = fotouser;
	}

	public String getExtensaofotouser() {
		return extensaofotouser;
	}

	public void setExtensaofotouser(String extensaofotouser) {
		this.extensaofotouser = extensaofotouser;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}
	
	public boolean getUseradmin() {
		return useradmin;

	}
	
	public boolean isNovo() {

		if (this.id == null) {
			return true; /* Inserir novo */
		} else if (this.id != null && this.id > 0) {
			return false; /* Atualizar */
		}

		return id == null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getMostraTelefoneRel() {
		
		String fone = "Telefone:\n\n";
		
		for (ModelTelefone modelTelefone : telefones) {
			fone += modelTelefone.getNumero() + "\n";
		}
		
		return fone;
	}
	
	

}
