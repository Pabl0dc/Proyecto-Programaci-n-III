// Call the dataTables jQuery plugin
$(document).ready(function() {
    verInfoEstudiante();    
});

async function verInfoEstudiante() {
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    if (!id) {
        alert("No se proporcionó un ID de estudiante.");
        return;
    }

    try {
        const response = await fetch('/api/estudiantes/' + id, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            alert("El estudiante no existe.");
            return;
        }

        const estudiante = await response.json();

        document.getElementById('botonCompleto').href = 'estudianteindividualcompleto.html?id=' + estudiante.id;
        document.getElementById('botonParcial').href = 'estudianteindividualparcial.html?id=' + estudiante.id;


        document.getElementById('txtNombre').value = estudiante.nombre;
        document.getElementById('txtApellido').value = estudiante.apellido;
        document.getElementById('txtEmail').value = estudiante.email;
        document.getElementById('txtTelefono').value = estudiante.telefono;
        document.getElementById('txtIdioma').value = estudiante.idioma;


    } catch (error) {
        console.error("Error al obtener los datos del estudiante:", error);
        alert("Ocurrió un error al consultar el estudiante.");
    }
}

