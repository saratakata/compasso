Feature: Testar api post
  Eu quero testar os fluxos de criação de usuário e seus dados

  Scenario Outline: Criar um usuário com sucesso
    Given O endereco da API para cadastra nova simulacao:
    And Eu tenha os seguintes dados do usuario
      | nome   | cpf   | email   | valor   | parcelas   | seguro   |
      | <nome> | <cpf> | <email> | <valor> | <parcelas> | <seguro> |
    When Realizar uma requisicao para cadastrar uma nova simulacao
    Then A API retorna o codigo "201"
    And A API retorna os dados cadastrados
      | nome   | cpf   | email   | valor   | parcelas   | seguro   |
      | <nome> | <cpf> | <email> | <valor> | <parcelas> | <seguro> |

    Examples: 
      | nome            | cpf         | email              | valor | parcelas | seguro |
      | Rafael Teixeira | 12345678021 | teste@teste.com.br |  1001 |        2 | true   |
      | Rafael Teixeira | 12345678015 | teste@teste.com.br | 40000 |       48 | false  |

  Scenario Outline: Tentar cadastra nova simulação com algum problema
    Given O endereco da API para cadastra novo usuario:
    And Eu tenha os seguintes dados do usuario
      | nome   | cpf   | email   | valor   | parcelas   | seguro   |
      | <nome> | <cpf> | <email> | <valor> | <parcelas> | <seguro> |
    When Realizar uma requisicao para cadastrar uma nova simulacao
    Then A API retorna o codigo <httpCode>
    And Retorna a mensagem <retorno>

    Examples: 
      | nome            | cpf         | email              | valor | parcelas | seguro | retorno                            | httpCode | OBS                                                                                                    |
      | Rafael Teixeira | 12345678021 | teste@teste.com.br |  1001 |        2 | true   | "CPF já existente"                 | "409"    | não está de acordo com a documentação portanto não passa está retornando 400 e a mensagemcpf duplicado |
      | Rafael Teixeira |             | teste@teste.com.br |  1001 |        2 | true   | "CPF em branco"                    | "400"    | NÃO está retornando mensagem de erro                                                                   |
      | Rafael Teixeira |         123 |                    |  1001 |        2 | true   | "E-mail deve ser um e-mail válido" | "400"    | nenhum 400 está retornando mensagem de erro                                                            |
