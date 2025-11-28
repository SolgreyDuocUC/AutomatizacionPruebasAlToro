Feature: Ingreso incorrecto con credenciales inválidas

  Background:
    Given que ingreso a la pagina de login en "http://testfire.net/login.jsp"

  Scenario: El usuario intenta acceder con credenciales inválidas
    When ingreso el nombre de usuario "user_invalido"
    And ingreso la contrasenia "pass_invalida"
    And presiono el boton de inicio de sesion
    Then se muestra el mensaje de error "Login Failed: We're sorry, but this username or password was not found in our system. Please try again."
    And no permanece en la URL principal "bank/main.jsp"
