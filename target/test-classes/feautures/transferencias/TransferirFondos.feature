Feature: Validar transferencia entre mis cuentas
  Yo como usuario necesito hacer transferencias entre mis cuentas

  Scenario: Transferencia entre mis cuentas
    Given que puedo acceder a la url "https://demo.testfire.net/"
    And puedo ingresar a mi aplicacion con mi usuario y mi password <FilaExcel>
    When doy click en el enlace de transferencia de fondos "Transfer Funds"
    And selecciono la cuenta de origen y destino <FilaExcel>
    And coloco el monto a transferir <FilaExcel>
    And doy click en el boton de transferencia "//input[@id='transfer']"
    Then aparece el mensaje de transferencia correcta <FilaExcel>

    Examples:
      | FilaExcel|
      | 1        |
      | 2        |
      | 5        |
 