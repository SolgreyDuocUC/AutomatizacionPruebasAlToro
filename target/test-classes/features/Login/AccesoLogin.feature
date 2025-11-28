Feature: Acceso desde la página principal al formulario de login
  Como usuario del sistema
  Quiero acceder al formulario de autenticación desde la página principal
  Para poder iniciar sesión en el sistema

  Background:
    Given que ingreso en la página principal de AltoroMutual en "http://testfire.net/"
    When presiono el botón para ir al formulario de login
    Then se muestra la página de login

  Scenario: El usuario accede directamente al formulario de autenticación
    Then el formulario de autenticación se carga correctamente

