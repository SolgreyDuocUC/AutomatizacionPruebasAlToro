Feature: Realizar una búsqueda en la página

  Background:
    Given el navegador está abierto
    And el usuario oprime el panel de Search

  Scenario: Búsqueda exitosa
    When el usuario ingresa el valor "test" en el panel de Search
    And realiza la búsqueda
    Then la página debería mostrar resultados relacionados con "test"

  Scenario: Búsqueda fallida
    When el usuario ingresa el valor "cadenaInexistente" en el panel de Search
    And realiza la búsqueda
    Then debería mostrarse un mensaje de respuesta no encontrado
