Feature: Buscar CPF

  Scenario Outline: Buscar restrição por cpf
    Given O endereco da API para buscar CPF:
    When Eu realizar a requisicao para buscar o cpf <cpf>
    Then A API retorna o codigo "200" e a mensagem "O CPF <cpf> tem problema"

    Examples: 
      | cpf         |
      | 97093236014 |
      | 60094146012 |
      | 84809766080 |
      | 62648716050 |
      | 26276298085 |
      | 01317496094 |
      | 55856777050 |
      | 19626829001 |
      | 24094592008 |
      | 58063164083 |

  Scenario Outline: Buscar CPF sem restrição
    Given O endereco da API para buscar CPF:
    When Eu realizar a requisicao para buscar o cpf <cpf>
    Then A API retorna o codigo "204" e a mensagem ""

    Examples: 
      | cpf         |
      | 00389923028 |
