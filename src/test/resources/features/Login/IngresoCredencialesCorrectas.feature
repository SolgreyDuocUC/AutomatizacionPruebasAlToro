Feature: Ingreso correcto con credenciales válidas
  Background:
    Given que ingreso a la pagina de login en "http://testfire.net/login.jsp"

  Scenario: El usuario accede correctamente al sistema con credenciales válidas
    When ingreso el nombre de usuario "Admin"
    And ingreso la contrasenia "Admin"
    And presiono el boton de inicio de sesion
    Then el sistema redirige al panel principal
    And se muestra el mensaje "Hello Admin User"
