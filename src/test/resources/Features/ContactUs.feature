Feature: Contactar con AltoroMutual a través del formulario Contact Us
  Yo como usuario necesito enviar consultas a través del formulario Contact Us

  Background:
    Given el navegador está abierto
    And que puedo acceder a la url "https://demo.testfire.net/"

  Scenario Outline: Enviar formulario de contacto correctamente
    When el usuario accede a la opción Contact Us
    And el usuario abre el formulario de contacto
    And el usuario ingresa el nombre de la fila "<FilaExcel>"
    And el usuario ingresa el email de la fila "<FilaExcel>"
    And el usuario ingresa el propósito de la fila "<FilaExcel>"
    And el usuario ingresa los comentarios de la fila "<FilaExcel>"
    And el usuario envía el formulario de contacto
    Then debería mostrarse confirmación de envío exitoso para la fila "<FilaExcel>"

    Examples:
      | FilaExcel |
      | 1         |
      | 2         |

  Scenario Outline: Limpiar formulario de contacto
    When el usuario accede a la opción Contact Us
    And el usuario abre el formulario de contacto
    And el usuario ingresa el nombre de la fila "<FilaExcel>"
    And el usuario ingresa el email de la fila "<FilaExcel>"
    And el usuario ingresa el propósito de la fila "<FilaExcel>"
    And el usuario ingresa los comentarios de la fila "<FilaExcel>"
    And el usuario limpia el formulario
    Then todos los campos deberían estar vacíos

    Examples:
      | FilaExcel |
      | 1         |

  Scenario Outline: Enviar formulario con campos inválidos
    When el usuario accede a la opción Contact Us
    And el usuario abre el formulario de contacto
    And el usuario ingresa el nombre de la fila "<FilaExcel>"
    And el usuario ingresa el email de la fila "<FilaExcel>"
    And el usuario ingresa el propósito de la fila "<FilaExcel>"
    And el usuario ingresa los comentarios de la fila "<FilaExcel>"
    And el usuario envía el formulario de contacto
    Then deberían mostrarse mensajes de error por campos inválidos

    Examples:
      | FilaExcel |
      | 3         |
