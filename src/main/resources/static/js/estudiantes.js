// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarEstudiantes();
  $('#estudiantes').DataTable({
    lengthChange: false,

    searching: false});

//  actualizarEmailDelEstudiante();
});

//function actualizarEmailDelEstudiante(){
//    document.getElementById('txt-email-estudiante').outerHTML = localStorage.email;
//}


async function cargarEstudiantes(){
  const request = await fetch('api/estudiantes', {
    method: 'GET',
    headers: getHeaders()
  });
  const estudiantes = await request.json();


  let listadoHtml = '';
  for (let estudiante of estudiantes){
    let botonEliminar = '<a href="#" onclick="eliminarEstudiante('+ estudiante.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
    let botonEditar = '<a href="estudianteindividual.html?id=' + estudiante.id + '" class="btn btn-secondary btn-circle"><i class="fas fa-info-circle"></i></a>';

    let estudianteHtml = '<tr><td>'+estudiante.id+'</td><td>'+estudiante.nombre+' '+estudiante.apellido+'</td><td>'+estudiante.email+'</td><td>'+estudiante.telefono+'</td><td>'+estudiante.idioma+'</td><td>' + botonEliminar + ' ' + botonEditar + '</td></tr>';
    listadoHtml += estudianteHtml;
  }

document.querySelector('#estudiantes tbody').outerHTML = listadoHtml;

}

function getHeaders(){
    return{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    }
}

async function eliminarEstudiante(id){

  if(!confirm('¿Desea eliminar este Estudiante?')){
    return;
  }

const request = await fetch('api/estudiantes/' + id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload()
}