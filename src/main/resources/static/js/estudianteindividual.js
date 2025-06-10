
$(document).ready(function() {
    cargarEstudiante();
});

async function cargarEstudiante() {
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    try {
        const response = await fetch('/api/estudiantes/' + id, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });
        const estudiante = await response.json();

        document.getElementById('txtNombre').value = estudiante.nombre;
        document.getElementById('txtApellido').value = estudiante.apellido;
        document.getElementById('txtEmail').value = estudiante.email;
        document.getElementById('txtTelefono').value = estudiante.telefono;
        document.getElementById('txtIdioma').value = estudiante.idioma;

    } catch (error) {
        alert("Datos del estudiante inválidos.");
    }
}

async function editar() {
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    let datos = {};
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
    const idiomasValidos = ["inglés", "ingles", "español", "espanol", "francés", "frances"];
    return idiomasValidos.includes(idioma.toLowerCase());
       }

    const idioma = document.getElementById('txtIdioma').value;
    if (!validacionIdioma(idioma)) {
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

    fetch('/api/estudiantes/' + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    }).then(response => {
        if (response.ok) {
            window.location.href = 'estudiantes.html';
        } else {
            alert("Error al guardar los cambios.");
        }
    });
}

async function editarParcial() {

    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    function validacionIdioma(idioma) {
        const idiomasValidos = ["inglés", "ingles", "español", "espanol", "francés", "frances"];
        return idiomasValidos.includes(idioma.toLowerCase());
      }

      const idioma = document.getElementById('txtIdioma').value;
      if (!validacionIdioma(idioma)) {
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


    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.telefono = document.getElementById('txtTelefono').value;
    datos.idioma = document.getElementById('txtIdioma').value;

    fetch('/api/estudiantes/' + id, {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    }).then(response => {
        if (response.ok) {
            window.location.href = 'estudiantes.html';
        } else {
            alert("Error al guardar los cambios parciales.");
        }
    });
}