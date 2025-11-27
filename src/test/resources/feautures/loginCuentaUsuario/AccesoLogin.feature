@AccesoLogin
Feature: Acceso directo al formulario de login
  Como usuario del sistema
  Quiero acceder directamente al formulario de autenticación
  Para poder iniciar sesión en el sistema

  Scenario: El usuario accede directamente al formulario de autenticación
    Given que ingreso directamente a la página de login en "http://testfire.net/login.jsp"
    Then el formulario de autenticación se carga correctamente








