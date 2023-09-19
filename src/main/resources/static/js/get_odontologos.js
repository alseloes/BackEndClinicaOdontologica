window.addEventListener('load', function () {
    (function(){

    const url = '/odontologos';
    const settings = {
        method: 'GET'
    };

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        for(odontologo of data){
            let table = document.getElementById('odontologoTable');
            let odontologoRow =table.insertRow();
            let tr_id = `tr_${odontologo.id}`;
            odontologoRow.id = tr_id;

            let deleteButton = '<button id="btn_delete_' + odontologo.id + '" type="button" onclick="deleteBy(' + odontologo.id + ')" class="btn btn-danger btn_delete">' +
                               '&times' +
                               '</button>';

            let updateButton = '<button id="btn_id_' + odontologo.id + '" type="button" onclick="findBy(' + odontologo.id + ')" class="btn btn-info btn_id">' + odontologo.id + '</button>';


            odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                                      '<td class=\"td_numeroDeMatricula\">' + odontologo.numeroDeMatricula.toUpperCase() + '</td>' +
                                      '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                                      '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                                      '<td>' + deleteButton + '</td>';
        };

    })
    })
    (function(){
    let pathname = window.location.pathname;
    if (pathname == './get_odontologos.html') {
        document.querySelector('.nav .nav-item a:last').classList.add('active');
    }
})


})