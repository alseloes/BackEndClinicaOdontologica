window.addEventListener('load', function () {
    (function(){

    //con fetch invocamos a la API de pacientes con el método GET
    //nos devolverá un JSON con una colección de pacientes
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    };

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
      //recorremos la colección de pacientes del JSON
        for(paciente of data){
            //por cada paciente armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el paciente
            let table = document.getElementById('pacienteTable');
            let pacienteRow =table.insertRow();
            let tr_id = `tr_${paciente.id}`;
            pacienteRow.id = tr_id;


            //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma,
            //dicho boton invocara a la funcion de javascript deleteByKey que se encargará de llamar a la API para
            //eliminar un paciente
            let deleteButton = '<button id="btn_delete_' + paciente.id + '" type="button" onclick="deleteBy(' + paciente.id + ')" class="btn btn-danger btn_delete">' +
                               '&times' +
                               '</button>';


            //por cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar el paciente que queremos
            //modificar y mostrar los datos del mismo en un formulario.
            let updateButton = '<button id="btn_id_' + paciente.id + '" type="button" onclick="findBy(' + paciente.id + ')" class="btn btn-info btn_id">' + paciente.id + '</button>';




            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos del paciente
            //como ultima columna el boton eliminar
            let domicilio = paciente.domicilio.calle.toUpperCase() + ', ' +
                           paciente.domicilio.numero.toUpperCase() + ', ' +
                           paciente.domicilio.localidad.toUpperCase() + ' - ' +
                           paciente.domicilio.provincia.toUpperCase();

            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                                      '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                                      '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                                      '<td class=\"td_dni\">' + paciente.dni.toUpperCase() + '</td>' +
                                      '<td class=\"td_fechaDeIngreso\">' + paciente.fechaDeIngreso + '</td>' +
                                      '<td class=\"td_domicilio\">' + domicilio + '</td>' +
                                      '<td class=\"td_email\">' + paciente.email.toLowerCase() + '</td>' +
                                      '<td>' + deleteButton + '</td>';
        };

    })
    })
    (function(){
    let pathname = window.location.pathname;
    if (pathname == './get_pacientes.html') {
        document.querySelector('.nav .nav-item a:last').classList.add('active');
    }
})


})