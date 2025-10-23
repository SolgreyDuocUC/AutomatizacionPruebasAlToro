Feature: Ingreso correcto con credenciales válidas
  Scenario: El usuario accede correctamente al sistema con credenciales válidas
    Given que accedo a la página de inicio de sesión en "http://testfire.net/login.jsp"
    When ingreso el usuario "Admin" y la contraseña "Admin"
    And hago clic en el botón de login
    Then el sistema redirige al panel principal y muestra el mensaje "Hello John Smith"
