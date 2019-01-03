# practica14

Una empresa nos solicita crear aun aplicación web que gestione la agenda de los gerentes, pidiendo de manera expresa que dicha función sea fácil de realizar. Los eventos gestionados en el calendario no soportarán recurrencia y en función al evento registrado el sistema debe notificar al gerente vía correo electrónico basado en lo configurado en la actividad. La aplicación será utilizada por varios usuarios y contar con una interfaz muestres los eventos registrados, parecido al Google Calendar.

Las librerías y componentes que necesitan utilizar en dicho desarrollo son:

• Vaadin (https://vaadin.com/docs/v10/flow/Overview.html)

• Mail: (http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-email.html)

• Scheduling Tasks: (https://spring.io/guides/gs/scheduling-tasks/)

• SMTP Gratuito: (https://www.sparkpost.com) o (https://www.mailjet.com/)

• Calendario Vaadin: (https://vaadin.com/directory/component/calendar-component, https://vaadin.com/directory/component/full-calendar-web-component)

Los requerimientos técnicos:
1. Debe existir un control de acceso para acceder a la aplicación.
2. Debe existir un CRUD de Gerentes para el admin.
3. El CRUD debe implementar Binder y Dataprovider con paginación de la información para las tablas.
4. Debe existir una opción donde el gerente configure su nombre y correo electrónicos.
5. Los eventos debe permitir cambio de fecha en función a la actualización directamente con el componente de calendario.
 