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

  //let repetirPassword = document.getElementById('txtRepetirPassword').value;

  //if(repetirPassword != datos.password){
    //alert('La contraseña que escribiste es diferente.');
    //return;
  //}

  const request = await fetch('api/estudiantes', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)

  });
    alert("¡La cuenta fue creada exitosamente! :D");
          window.location.href = 'login.html'
}