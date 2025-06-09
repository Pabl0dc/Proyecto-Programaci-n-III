// Call the dataTables jQuery plugin
$(document).ready(function() {
//on ready
});


async function registrarEstudiante(){
  let datos ={};
  datos.nombre = document.getElementById('txtNombre').value;
  datos.apellido = document.getElementById('txtApellido').value;
  datos.email = document.getElementById('txtEmail').value;
  datos.telefono = document.getElementById('txtTelefono').value;
  datos.idioma = document.getElementById('txtIdioma').value;

  //let Idioma = document.getElementById('idioma').value;

  //if(repetirIdioma != datos.idioma){
    //alert('El idioma que escribiste es diferente.');
    //return;
  //}

  function validacionIdioma(idioma) {
    return ["inglés", "español", "francés"].includes(idioma.toLowerCase());
  }

  if (!validacionIdioma(datos.idioma)) {
      alert("Idioma inválido. Debe ser inglés, español o francés.");
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
    //alert("¡La cuenta fue creada exitosamente! :D");
          window.location.href = 'estudiantes.html'
}