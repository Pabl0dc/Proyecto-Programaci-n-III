// Call the dataTables jQuery plugin
$(document).ready(function() {
//on ready
});


async function registrarEstudiante(){

  const parametro = new URLSearchParams(window.location.search);
  const id = parametro.get('id');

  let datos ={};
  datos.nombre = document.getElementById('txtNombre').value;
  datos.apellido = document.getElementById('txtApellido').value;
  datos.email = document.getElementById('txtEmail').value;
  datos.telefono = document.getElementById('txtTelefono').value;
  datos.idioma = document.getElementById('txtIdioma').value;

  if (!datos.nombre.trim()) {
            alert('El nombre no puede estar vacío.');
            return;
        }

  if (!datos.apellido.trim()) {
            alert('El apellido no puede estar vacío.');
            return;
        }

  function validacionIdioma(idioma) {
    return ["inglés", "español", "francés"].includes(idioma.toLowerCase());
  }

  if (!validacionIdioma(datos.idioma)) {
      alert("Idioma inválido. Debe ser inglés, español o francés.");
      return;
    }

  const email = document.getElementById('txtEmail').value;
  if (!/\S+@\S+\.\S+/.test(email)) {
      alert('Correo electrónico no válido.');
      return;
    }

  const telefono = document.getElementById('txtTelefono').value;
  if (!/^\d{10}$/.test(telefono)) {
      alert('El teléfono debe contener exactamente 10 dígitos numéricos.');
      return;
    }

  const response = await fetch('/api/estudiantes', {
          method: 'GET',
          headers: {
              'Accept': 'application/json'
          }
      });

      const estudiantes = await response.json();

      const correoYaExiste = estudiantes.some(est => est.email === email && est.id != id);
      if (correoYaExiste) {
          alert('Ya existe un estudiante con ese correo electrónico.');
          return;
      }

  const request = await fetch('api/estudiantes', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)

  });
    alert("¡La cuenta fue creada exitosamente! :D");
          window.location.href = 'estudiantes.html'
}