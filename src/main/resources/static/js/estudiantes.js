// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarEstudiantes();
  $('#estudiantes').DataTable();

  actualizarEmailDelEstudiante();
});

function actualizarEmailDelEstudiante(){
    document.getElementById('txt-email-estudiante').outerHTML = localStorage.email;
}


async function cargarEstudiantes(){
  const request = await fetch('api/estudiantes', {
    method: 'GET',
    headers: getHeaders()
  });
  const estudiantes = await request.json();


  let listadoHtml = '';
  for (let estudiante of estudiantes){
    let botonEliminar = '<a href="#" onclick="eliminarEstudiante('+ estudiante.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
    let usuarioHtml = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+telefonoTexto+'</td><td>' + botonEliminar + '</td></tr>';
    listadoHtml += usuarioHtml;
  }

document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}

function getHeaders(){
    return{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

async function eliminarUsuario(id){

  if(!confirm('¿Desea eliminar este usuario?')){
    return;
  }

const request = await fetch('api/usuarios/' + id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload()
}