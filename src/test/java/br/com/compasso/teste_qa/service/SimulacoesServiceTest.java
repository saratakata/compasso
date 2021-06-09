package br.com.compasso.teste_qa.service;

import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class SimulacoesServiceTest {

	private String requestBody = "";
	Response response = null;
	
	@Given("O endereco da API para cadastra nova simulacao:")
	public void o_endereco_da_API_para_cadastra_novo_usuario() throws Throwable {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Given("^Eu tenha os seguintes dados do usuario$")
	public void eu_tenha_os_seguintes_dados_do_usuario(DataTable usuario) throws Throwable {
		List<Map<String, String>> dadosUsuario = usuario.asMaps(String.class, String.class);

		this.requestBody = "{\r\n" + "\"nome\": \"" + dadosUsuario.get(0).get("nome") 
				+ "\",\r\n" + "\"cpf\": " + dadosUsuario.get(0).get("cpf") 
				+ ",\r\n" + "\"email\": \"" + dadosUsuario.get(0).get("email")				
				+ "\",\r\n" + "\"valor\": " + dadosUsuario.get(0).get("valor") 
				+ ",\r\n" + "\"parcelas\": " + dadosUsuario.get(0).get("parcelas")
				+ ",\r\n" + "\"seguro\":" + dadosUsuario.get(0).get("seguro") + "\r\n}";
		System.out.println("this.requestBody ================= > " + this.requestBody);
	}

//	@Given ("o mesmo ja esteja cadastrado")
//	public void chamar_req_de_cadastrar_usuario() throws Throwable {
//		this.realizar_uma_requisicao_para_cadastrar_o_usuario();
//	}
	
	@When("^Realizar uma requisicao para cadastrar uma nova simulacao$")
	public void realizar_uma_requisicao_para_cadastrar_o_usuario() throws Throwable {
		
		try {
			response = RestAssured.given().contentType(ContentType.JSON).body(this.requestBody)
					.post("/api/v1/simulacoes");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@When("^Realizar uma requisicao para altera uma simulacao (\\d+)$")
	public void realizar_uma_requisicao_para_alterar_simulacao(String cpf) throws Throwable {
		 System.out.println("CPF ===========> " + cpf);
		try {
			response = RestAssured.given().contentType(ContentType.JSON).body(this.requestBody)
					.put("/api/v1/simulacoes/" + cpf );
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Then("^A API retorna os dados cadastrados$")
	public void a_API_retorna_os_dados_cadastrados(DataTable usuario) throws Throwable {
		List<Map<String, String>> dadosUsuario = usuario.asMaps(String.class, String.class);
				
		assertTrue(this.response.asString().contains(dadosUsuario.get(0).get("nome")));
		assertTrue(this.response.asString().contains(dadosUsuario.get(0).get("cpf")));
		assertTrue(this.response.asString().contains(dadosUsuario.get(0).get("email")));
		assertTrue(this.response.asString().contains(dadosUsuario.get(0).get("valor")));
		assertTrue(this.response.asString().contains(dadosUsuario.get(0).get("parcelas")));
		assertTrue(this.response.asString().contains(dadosUsuario.get(0).get("seguro")));
	}
	
	@Then("^A API retorna o codigo \"([^\"]*)\"$")
	public void a_API_retorna_retorna_o_codigo(String code) throws Throwable {		
		System.out.println("code => " + response.getStatusCode() + "    this.response.asString() => " +this.response.asString() );
		assertEquals(code, response.getStatusCode()+""); 
	}
	
	@Then("^Retorna a mensagem \"([^\"]*)\"$")
	public void retorna_a_mensagem(String msgErro) throws Throwable {
		assertTrue(this.response.asString().contains(msgErro));
	}

	
	

//	@Given("^a pessoa esteja cadastrada$")
//	public void a_pessoa_esteja_cadastrada() throws Throwable {
//	   
//	}

	
	
	
//	@When("^Eu realizar a requisicao para buscar o cpf (\\d+)$")
//	public void eu_realizar_a_requisicao_para_buscar_o_cpf(String cpf) throws Throwable {
//		System.out.println("CPF =====> " + cpf);
//		response = RestAssured.given().contentType(ContentType.JSON).get("/api/v1/restricoes/"+cpf);
//	}
	
	
	
//	@When("^Eu realizar a requisicao para buscar o cpf com os seguintes dados$")
//	public void eu_realizar_a_requisicao_para_buscar_a_pessoa_com_os_seguintes_dados(DataTable cpf) throws Throwable {
//		List<Map<String, String>> dadosUsuario = cpf.asMaps(String.class, String.class);
//		try {
//			response = RestAssured.given().contentType(ContentType.JSON).get("/pessoas/"+dadosUsuario.get(0).get("ddd")+"/"+dadosUsuario.get(0).get("numTelefone"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}


	
	
	
}
