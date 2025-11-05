Feature: Ingreso incorrecto con credenciales inválidas
  Scenario: El usuario intenta acceder al sistema con credenciales inválidas
    Given que abro la página de login en "http://testfire.net/login.jsp"
    When escribo el nombre de usuario "Anibal" y la clave "Anib12345."
    And hago clic para iniciar sesión
    Then el sistema muestra un mensaje de error que contiene "Login Failed"
