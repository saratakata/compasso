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

public class PessoaServiceTest {

	private String requestBody = "";
	Response response = null;
	
	@Given("O endereco da API para cadastra novo usuario:")
	public void o_endereco_da_API_para_cadastra_novo_usuario() throws Throwable {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	
	@Then("^A API retorna o codigo \"([^\"]*)\" e a mensagem \"([^\"]*)\"$")
	public void a_API_retorna_retorna_o_codigo(String code, String msgErro) throws Throwable {
		System.out.println("code =>" + code  + "responsecode => " + response.getStatusCode()+"");
		assertEquals(code, response.getStatusCode()+""); 
		assertTrue(this.response.asString().contains(msgErro));
	}
	@Given("^O endereco da API para buscar CPF:$")
	public void o_endereco_da_API_para_buscar_pessoa() throws Throwable {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}


	@When("^Eu realizar a requisicao para buscar o cpf (\\d+)$")
	public void eu_realizar_a_requisicao_para_buscar_o_cpf(String cpf) throws Throwable {
		System.out.println("CPF =====> " + cpf);
		response = RestAssured.given().contentType(ContentType.JSON).get("/api/v1/restricoes/"+cpf);
	}	
	
}
