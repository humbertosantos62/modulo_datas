<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
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
													<h4 style="color: black;">Cadastro de Telefones</h4>
													<div class="card-block">
													
					<form class="form-material" action="<%= request.getContextPath() %>/ServletTelefone" method="post" id="formFone">								
						<div class="form-group form-default form-static-label">
                        <input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${modelLogin.id }">
                        <span class="form-bar"></span>
                        <label class="float-label">ID User:</label>              
                        </div>
                        
                        <div class="form-group form-default form-static-label">
                        <input readonly="readonly" type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                        <span class="form-bar"></span>
                        <label class="float-label">Nome:</label>
                        </div> 
                        
                        <div class="form-group form-default form-static-label">
                        <input  type="text" name="numero" id="numero" class="form-control" required="required" >
                        <span class="form-bar"></span>
                        <label class="float-label">N?mero:</label>
                        </div>
                                                            
                        <button  class="btn btn-success waves-effect waves-light">Salvar</button>
					
					</form>								
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>
										
										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaresultadosview">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">N?mero</th>
														<th scope="col">Excluir</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${modelTelefones}' var='f'>
													      <tr>
													       <td><c:out value="${f.id}"></c:out></td>
													       <td><c:out value="${f.numero}"></c:out></td>
													       <td><a class="btn btn-warning" href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${f.id}&userpai=${modelLogin.id}" >Excluir</a></td>
													      </tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
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

<script type="text/javascript">

$("#numero").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
});


</script>
  
</body>

</html>
