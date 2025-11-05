Feature: Ingreso correcto con credenciales válidas
  Scenario: El usuario accede correctamente al sistema con credenciales válidas
    Given que ingreso a la página de login en "http://testfire.net/login.jsp"
    When ingreso el nombre de usuario "Admin" y la contraseña "Admin"
    And presiono el botón de inicio de sesión
    Then el sistema redirige al panel principal y muestra el mensaje "Hello Admin User"
