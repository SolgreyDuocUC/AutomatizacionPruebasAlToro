Feature: Transferir fondos entre cuentas

  Background:
    Given el navegador está abierto
    And el usuario está logeado

  Scenario: Transferir fondos correctamente
    When el usuario oprime el botón Transfer Funds
    And el usuario selecciona la cuenta desde la cual transferir
    And el usuario selecciona la cuenta destino
    And el usuario ingresa el monto a transferir
    And el usuario confirma la transacción
    Then deberia mostrarse una confirmación de transferencia exitosa

  Scenario: Intentar transferir monto inválido
    When el usuario oprime el botón Transfer Funds
    And el usuario selecciona la cuenta desde la cual transferir
    And el usuario selecciona la cuenta destino
    And el usuario ingresa un monto inválido a transferir
    And el usuario confirma la transacción
    Then deberia mostrarse un mensaje de error indicando monto inválido