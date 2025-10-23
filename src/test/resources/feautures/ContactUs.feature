Feature: Contactar con AltoroMutual a través del formulario Contact Us

  Background:
    Given el navegador está abierto
    And el usuario está en la página principal

  Scenario: Enviar formulario de contacto correctamente
    When el usuario accede a la opción Contact Us
    And el usuario abre el formulario de contacto
    And el usuario ingresa el nombre "Juan Pérez"
    And el usuario ingresa el email "juan.perez@example.com"
    And el usuario ingresa el propósito "Consulta general"
    And el usuario ingresa los comentarios "Necesito información sobre cuentas"
    And el usuario envía el formulario de contacto
    Then debería mostrarse confirmación de envío exitoso

  Scenario: Limpiar formulario de contacto
    When el usuario accede a la opción Contact Us
    And el usuario abre el formulario de contacto
    And el usuario ingresa datos en todos los campos
    And el usuario limpia el formulario
    Then todos los campos deberían estar vacíos

  Scenario: Enviar formulario con campos inválidos
    When el usuario accede a la opción Contact Us
    And el usuario abre el formulario de contacto
    And el usuario ingresa el nombre ""
    And el usuario ingresa el email "email-invalido"
    And el usuario ingresa el propósito ""
    And el usuario ingresa los comentarios ""
    And el usuario envía el formulario de contacto
    Then deberían mostrarse mensajes de error por campos inválidos
