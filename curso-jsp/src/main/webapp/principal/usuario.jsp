<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    %>

<!DOCTYPE html>
<html lang="pt-pt">

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="head.jsp"></jsp:include>

  <body>
  
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  
  
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
		
		<jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
              <jsp:include page="navbarmainmenu.jsp"></jsp:include>

                  <div class="pcoded-content">
                      <!-- Page-header start -->
                   <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                      
                      
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                        
                                   <div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
					<h4 style="color: black;">Cadastro de Usuário</h4>
						<div class="card-block">
						
			<form class="form-material"  enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
					     
			<input type="hidden" name="acao" id="acao" value="">
									     
            <div class="form-group form-default form-static-label">
                <input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${modolLogin.id }">
                <span class="form-bar"></span>
                <label class="float-label">ID:</label>              
            </div> 
            
            <div class="form-group form-default input-group mb4">
            	<div class="input-group-prepend">
            	
            	<c:if test="${modolLogin.fotouser != '' && modolLogin.fotouser != null}">
            	 <a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${modolLogin.id}">
                 <img alt="Imagem User" id="fotoembase64" src="${modolLogin.fotouser}" width="70px">
                 </a>
            	</c:if>
            	
            	<c:if test="${modolLogin.fotouser == '' ||  modolLogin.fotouser == null }">
            		<img alt="Imagem User" id="fotoembase64" src="assets/images/transferir.jpeg"   width="70px">            	
            	</c:if>
            	           	
                </div>
            	<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 10px; margin-left: 5px; color: blue;">
            </div>
                                                                                                                                                                              
            <div class="form-group form-default form-static-label">
                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modolLogin.nome }">
                <span class="form-bar"></span>
                <label class="float-label">Nome:</label>
            </div>
            
             <div class="form-group form-default form-static-label">
                <input type="text" name="dataNascimento" id="dataNascimento" class="form-control" required="required" value="${modolLogin.dataNascimento}">
                <span class="form-bar"></span>
                <label class="float-label">Data Nascimento:</label>
            </div>
            
             <div class="form-group form-default form-static-label">
                <input type="text" name="rendamensal" id="rendamensal" class="form-control" required="required" value="${modolLogin.rendamensal}">
                <span class="form-bar"></span>
                <label class="float-label">Renda Mensal</label>
            </div>
                                           
            <div class="form-group form-default form-static-label">
                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modolLogin.email }">
                <span class="form-bar"></span>
                <label class="float-label">E-mail: (ex@email.com)</label>
            </div>
            
           <div class="form-group form-default form-static-label">
            <select class="form-control" aria-label="Default select example" name="perfil">
		     <option disabled="disabled" selected="selected">[Selecione o Perfil]</option>
		  
		     <option value="ADMIN" <% 
		  
		      ModelLogin modelLogin = (ModelLogin) request.getAttribute("modolLogin");
		  
		     if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
			  out.print(" ");
			  out.print("selected=\"selected\"");
			  out.print(" ");
			  }  %> >Administrador</option>
		  
		  
		     <option value="SECRETARIO(A)"<%
              modelLogin = (ModelLogin) request.getAttribute("modolLogin");
		  
			  if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIO(A)") ){
				  out.print(" ");
				  out.print("selected=\"selected\"");
				  out.print(" ");
				}  %>>Secretário(a)</option>
		  
		       <option value="AUXILIAR"<%
		  
			  modelLogin = (ModelLogin) request.getAttribute("modolLogin");
		  
			  if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
				  out.print(" ");
				  out.print("selected=\"selected\"");
				  out.print(" ");
				}  %>>Auxiliar</option>
		  
		</select>
		 <span class="form-bar"></span>
         <label class="float-label">Perfil</label>
		</div>
		
		<div class="form-group form-default form-static-label">
              <input onblur="pesquisaCodPostal();" type="text" name="Codigopostal" id="Codigopostal" class="form-control" required="required" autocomplete="off" value="${modolLogin.codigopostal}">
              <span class="form-bar"></span>
              <label class="float-label">Código Postal</label>
        </div>
                                             
        <div class="form-group form-default form-static-label">
              <input type="text" name="Morada" id="Morada" class="form-control" required="required" autocomplete="off" value="${modolLogin.morada}">
              <span class="form-bar"></span>
              <label class="float-label">Morada</label>
        </div> 
        
         <div class="form-group form-default form-static-label">
              <input type="text" name="Localidade" id="Localidade" class="form-control" required="required" autocomplete="off" value="${modolLogin.localidade}">
              <span class="form-bar"></span>
              <label class="float-label">Localidade</label>
        </div> 
                                            
        <div class="form-group form-default form-static-label">
              <input type="text" name="Freguesia" id="Freguesia" class="form-control" required="required" autocomplete="off" value="${modolLogin.freguesia}">
              <span class="form-bar"></span>
              <label class="float-label">Freguesia</label>
         </div>     
                                             
         <div class="form-group form-default form-static-label">
               <input type="text" name="Concelho" id="Concelho" class="form-control" required="required" autocomplete="off" value="${modolLogin.concelho}">
               <span class="form-bar"></span>
               <label class="float-label">Concelho</label>
         </div>     
                                             
         <div class="form-group form-default form-static-label">
               <input type="text" name="Distrito" id="Distrito" class="form-control" required="required" autocomplete="off" value="${modolLogin.distrito}">    
               <span class="form-bar"></span>
               <label class="float-label">Distrito</label>
          </div>                                                                                                               
             
             <div class="form-group form-default form-static-label">
                 <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${modolLogin.login }">
                 <span class="form-bar"></span>
                 <label class="float-label">Login:</label>
             </div>                                         
                              
            <div class="form-group form-default form-static-label">
                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modolLogin.senha }">
                <span class="form-bar"></span>
                <label class="float-label">Senha</label>
            </div>
            
            <div class="form-group form-default form-static-label">
              <input type="radio" name="sexo" checked="checked" value="MASCULINO"  <%
  
		     modelLogin = (ModelLogin) request.getAttribute("modolLogin");
  
				  if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
					  out.print(" ");
					  out.print("checked=\"checked\"");
					  out.print(" ");
					}  %>>Masculino</>
                                             
                                             
               <input type="radio" name="sexo" value="FEMININO" <%
  
		        modelLogin = (ModelLogin) request.getAttribute("modolLogin");
  
				  if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
					  out.print(" ");
					  out.print("checked=\"checked\"");
					  out.print(" ");
					}  %>>Feminino</>
           </div>
                                                                                                                                                                                                                                  
            
            <button type="button"  class="btn btn-primary btn-round waves-effect waves-light" onclick="limparForm();">Novo</button>
            <button type="submit"  class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
            <button type="button" class= "btn btn-info btn-round waves-effect waves-light" onclick="criarDeleteComAjax();">Excluir</button>
             <c:if test="${modolLogin.id > 0}">  
			  <a href="<%= request.getContextPath() %>/ServletTelefone?iduser=${modolLogin.id}" class="btn btn-danger btn-round waves-effect waves-light" >Telefone</a>
	        </c:if>
            <!--  <button class="btn btn-warning btn-round waves-effect waves-light">Warning Button</button>
            <button class="btn btn-danger btn-round waves-effect waves-light">Danger Button</button>
            <button class="btn btn-inverse btn-round waves-effect waves-light">Inverse Button</button>
            <button class="btn btn-disabled btn-round disabled waves-effect waves-light">Disabled Button</button>-->
            <button type="button" class="btn btn-warning btn-round" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
      		</form>
				</div>
				</div>
				</div>
                                   
                      </div>
                      <span  id="msg" style="color:#2a2443; font-size: 20px;">${msg}</span>
<div style="height: 300px;overflow: scroll;" >
	
     <table class="table" id="tabelaresultadosview">
        <thead>
           <tr>
           
           <th scope="col">ID</th>
           <th scope="col">Nome</th>
           <th scope="col">Perfil</th>
           <th scope="col">Ver</th>
           </tr>
         </thead>
         <tbody>
 		  	<c:forEach items='${modelLogins}' var='ml'>
		      <tr>
		       <td><c:out value="${ml.id}"></c:out></td>
		       <td><c:out value="${ml.nome}"></c:out></td>
		       <td><c:out value="${ml.perfil}"></c:out></td>
		       <td><a class="btn btn-success" href="<%= request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
		      </tr>
			 </c:forEach>
         </tbody>
     </table>
</div>	


<nav aria-label="Page navigation example">
	<ul class="pagination">
				
	  <%
	     int totalPagina = (int) request.getAttribute("totalPagina");
	   
	    for (int p = 0; p < totalPagina; p++){
	    	String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 5);  
	    	out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+ url +"\">"+(p + 1)+"</a></li>");
	    }
	   
	   %>
	
		
	</ul>
</nav>	|

                    </div>
                  <!-- Page-body end -->
                </div>
              <div id="styleSelector"> </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
   
<jsp:include page="javascriptfile.jsp"></jsp:include>  

<!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

	<div class="input-group mb-3">
	  <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
	  <div class="input-group-append">
	    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
	  </div>
	</div>
	
<div style="height: 300px;overflow: scroll;" >	
	<table class="table" id="tabelaresultados">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nome</th>     
      <th scope="col">Ver</th>
    </tr>
  </thead>
  <tbody>
    
  </tbody>
</table>
</div>

<nav aria-label="Page navigation example">
<ul class="pagination" id="ulPaginacaoUserAjax">
											
</ul>
</nav>

<span id="totalResultados"></span>
</div>

<div class="modal-footer">
    <button type="button" class="btn btn-warning" data-dismiss="modal">Fechar</button>
</div>
    </div>
  </div>
</div>

<script type="text/javascript">
$("#rendamensal").maskMoney({showSymbol:true, symbol:"€ ", decimal:",", thousands:"."});

const formatter = new Intl.NumberFormat('pt-PT', {
    currency : 'EUR',
    minimumFractionDigits : 2
});

$("#rendamensal").val(formatter.format($("#rendamensal").val()));

$("#rendamensal").focus();

var dataNascimento = $("#dataNascimento").val();
if(dataNascimento != null && dataNascimento != ''){

var dateFormat = new Date(dataNascimento);

$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-PT',{timeZone: 'UTC'}));
}
$("#nome").focus();


$( function() {
	  
	  $("#dataNascimento").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );

$("#numero").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
});

$("#Codigopostal").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
});


function pesquisaCodPostal() {
    var Codigopostal = $("#Codigopostal").val();
    
    $.getJSON("https://api.duminio.com/ptcp/ptapi60908f806103c5.46393431/" + Codigopostal +"?callback?" , function(dados) { 

	if (!("erro" in dados)) {
	        $("#Codigopostal").val(dados.Codigopostal);
	        $("#Morada").val(dados.Morada);
            $("#Localidade").val(dados.Localidade);
            $("#Freguesia").val(dados.Freguesia);
            $("#Concelho").val(dados.Concelho);
            $("#Distrito").val(dados.Distrito);
	}
	    

    });
}

function visualizarImg(fotoembase64, fileFoto) {
    
    
    var preview = document.getElementById(fotoembase64); // campo IMG html
    var fileUser = document.getElementById(fileFoto).files[0];
    var reader = new FileReader();
    
    reader.onloadend = function (){
	    preview.src = reader.result; /*Carrega a foto na tela*/
    };
    
    if (fileUser) {
	  reader.readAsDataURL(fileUser); /*Preview da imagem*/
    }else {
	 preview.src=  '';
    }
    
}

function verEditar(id) {
	   
    var urlAction = document.getElementById('formUser').action;
    
    
    window.location.href = urlAction + '?acao=buscarEditar&id='+id;
    
}

function buscaUserPagAjax(url){
   
    
    var urlAction = document.getElementById('formUser').action;
    var nomeBusca = document.getElementById('nomeBusca').value;
    
	 $.ajax({	     
	     method: "get",
	     url : urlAction,
	     data : url,
	     success: function (response, textStatus, xhr) {
		 
		 var json = JSON.parse(response);
		 
		 
		 $('#tabelaresultados > tbody > tr').remove();
		 $("#ulPaginacaoUserAjax > li").remove();
		 
		  for(var p = 0; p < json.length; p++){
		      $('#tabelaresultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td> '+json[p].nome+'</td> <td><button onclick="verEditar('+json[p].id+')" type="button" class="btn btn-info">Ver</button></td></tr>');
		  }
		  
		  document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
		  
		    var totalPagina = xhr.getResponseHeader("totalPagina");
	
		  
		    
			  for (var p = 0; p < totalPagina; p++){
			      
		
			      
			      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
			      
			   
			      $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>'); 
			      
			  }
		 
	     }
	     
	 }).fail(function(xhr, status, errorThrown){
	    alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
	 });
    
}

function buscarUsuario() {
    
    var nomeBusca = document.getElementById('nomeBusca').value;
    
    if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){ /*Validando que tem que ter valor pra buscar no banco*/
	
	 var urlAction = document.getElementById('formUser').action;
	
	 $.ajax({
	     
	     method: "get",
	     url : urlAction,
	     data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
	     success: function (response, textStatus, xhr) {
		 
		 var json = JSON.parse(response);
		 
		 
		 $('#tabelaresultados > tbody > tr').remove();
		 $("#ulPaginacaoUserAjax > li").remove();
		 
		  for(var p = 0; p < json.length; p++){
		      $('#tabelaresultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td> '+json[p].nome+'</td> <td><button onclick="verEditar('+json[p].id+')" type="button" class="btn btn-info">Ver</button></td></tr>');
		  }
		  
		  document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
		  
		    var totalPagina = xhr.getResponseHeader("totalPagina");
	
		  
		    
			  for (var p = 0; p < totalPagina; p++){
			      
			      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
			      
			   
			      $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
			      
			  }
		 
	     }
	     
	 }).fail(function(xhr, status, errorThrown){
	    alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
	 });
	
	
    }
    
}

function criarDeleteComAjax() {
    
    if (confirm('Deseja realmente excluir os dados?')){
	
	 var urlAction = document.getElementById('formUser').action;
	 var idUser = document.getElementById('id').value;
	 
	 $.ajax({
	     
	     method: "get",
	     url : urlAction,
	     data : "id=" + idUser + '&acao=deletarajax',
	     success: function (response) {
		 
		  limparForm();
		  document.getElementById('msg').textContent = response;
	     }
	     
	 }).fail(function(xhr, status, errorThrown){
	    alert('Erro ao deletar usuário por id: ' + xhr.responseText);
	 });
	 
	  
    }
    
}

function criarDelete() {
if(confirm('Deseja realmente excluir os dados?')){

    
    document.getElementById("formUser").method = 'get';
    document.getElementById("acao").value = 'deletar';
    document.getElementById("formUser").submit();
    }
}

function limparForm() {
    
    var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/
    
    for (p = 0; p < elementos.length; p ++){
	    elementos[p].value = '';
    }
}
</script>
</body>

</html>
