Feature: Testar api put
  Eu quero testar os fluxos de alteração  de usuário e seus dados

  Scenario Outline: Criar um usuário com sucesso
    Given O endereco da API para cadastra nova simulacao:
    And Eu tenha os seguintes dados do usuario
      | nome   | cpf   | email   | valor   | parcelas   | seguro   |
      | <nome> | <cpf> | <email> | <valor> | <parcelas> | <seguro> |
    When Realizar uma requisicao para altera uma simulacao <cpf>
    Then A API retorna o codigo "200"
    And A API retorna os dados cadastrados
      | nome   | cpf   | email   | valor   | parcelas   | seguro   |
      | <nome> | <cpf> | <email> | <valor> | <parcelas> | <seguro> |

    Examples: 
      | nome            | cpf         | email                 | valor | parcelas | seguro |
      | Rafael Teixeira | 12345678021 | testando@teste.com.br |  1001 |        2 | true   |
      #| Rafael Teixeira | 12345678015 | teste@teste.com.br    | 40000 |       48 | false  |
