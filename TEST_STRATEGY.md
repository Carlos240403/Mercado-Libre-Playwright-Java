What would you NOT automate in this flow, and why?

No automatizaría aspectos puramente visuales como colores, tipografías, espacios o estilos generales de la página. Estos elementos suelen cambiar frecuentemente y normalmente no impactan la funcionalidad principal del negocio, por lo que automatizarlos vuelve las pruebas más frágiles y costosas de mantener.

Tampoco intentaría automatizar CAPTCHA o mecanismos anti-bot. Estas protecciones están diseñadas específicamente para prevenir automatización y no forman parte del flujo funcional que se busca validar. Intentar automatizarlas agregaría inestabilidad innecesaria a la suite de pruebas.
---                         ---                 ----                    ----

If MercadoLibre added a CAPTCHA to the search flow, how would you handle it in your test suite?

Durante la implementación de este reto, sí aparecieron mecanismos CAPTCHA y validaciones anti-bot en diferentes partes del flujo.

El primer caso ocurrió al aplicar el filtro de origen del envío, donde MercadoLibre mostró un CAPTCHA que impedía continuar con la automatización. En ese caso decidí excluir ese filtro específico para mantener estable la ejecución del flujo principal.

Posteriormente apareció otra validación anti-bot al intentar ordenar por menor precio. Aunque no era un CAPTCHA tradicional, sí mostraba una pantalla de detección de automatización. Para manejarlo, moví esa validación hacia el final del flujo, permitiendo que el resto de validaciones pudiera completarse correctamente.

En un entorno real, normalmente:

evitaría automatizar CAPTCHA directamente, aislaría esos flujos, utilizaría ambientes de prueba sin protecciones anti-bot cuando sea posible, o trabajaría con el equipo de desarrollo para habilitar bypasses controlados en ambientes QA.

---                         ---                 ----                    ----

What flakiness risks exist in this test, and how did you mitigate them?

Existen varios riesgos de inestabilidad en este tipo de automatización:

Los datos cambian constantemente (precios, nombres y disponibilidad).
Los resultados del UI no siempre coinciden exactamente con el API.
El API puede fallar intermitentemente o devolver respuestas 403.
La estructura de la página y los selectores pueden cambiar.
El sitio puede detectar automatización y alterar el comportamiento dinámicamente.

Para mitigar estos riesgos:

Evité validaciones excesivamente estrictas y utilicé lógica flexible basada en palabras clave.
Si el API falla, el test no falla innecesariamente.
Utilicé selectores más robustos y múltiples estrategias de localización.
Agregué esperas controladas para reducir problemas de timing.
Me enfoqué en validar funcionalidad de negocio y no valores estáticos exactos.

El objetivo fue mantener una automatización resistente sin perder cobertura funcional relevante.

---                         ---                 ----                    ----

If you had to add this to a team's CI pipeline running 50+ other test suites, what would you change?

Si esta prueba formara parte de un pipeline con muchas suites ejecutándose, mi principal enfoque sería mejorar confiabilidad, eficiencia de ejecución y reducir ruido dentro del CI.

No ejecutaría este tipo de prueba UI completa en cada build, ya que las pruebas UI suelen ser más lentas y más propensas a inestabilidad que validaciones a niveles inferiores. Lo más probable es que las movería a ejecuciones nightly, smoke suites o regresiones críticas.

También reduciría la dependencia del UI lo más posible. El UI es útil para validar flujos end-to-end, pero a gran escala la mayoría de validaciones deberían resolverse mediante APIs o pruebas de servicio, dejando el UI únicamente para validar interacciones críticas del usuario.

Otro punto importante sería manejar correctamente la inestabilidad externa. En este reto, el API ocasionalmente devolvía respuestas 403 y MercadoLibre activaba protecciones anti-bot, por lo que el framework se diseñó para no fallar innecesariamente debido a factores externos. Esto es clave en pipelines grandes, donde pruebas inestables pueden afectar la confiabilidad de toda la suite.

Finalmente, me aseguraría de que la prueba sea completamente independiente y aislada, evitando dependencias de sesiones previas, estados específicos de datos o ambientes compartidos. En automatización a escala, el aislamiento es fundamental para facilitar mantenimiento y debugging