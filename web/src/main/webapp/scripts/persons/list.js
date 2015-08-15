/**
 * Created by andreas on 2/12/15.
 */

$(function() {

    var colNames = [
        'id',
        'firstname',
        'lastName',
        'email',
        'birthdate'
    ];

    var colModel = [
        { name: 'id', index: 'id', sortable: false, width: 100, editable: false, editoptions: { defaultValue: null} },
        { name: 'firstName', index: 'firstName', sortable: true, width: 250, editable: true },
        { name: 'lastName', index: 'lastName', sortable: true, widht: 250, editable:true },
        { name: 'eMail', index: 'eMail', sortable: true, widht: 250, editable: true },
        { name: 'birthDate', index: 'birthDate', sortable: true, widht: 250, editable: true }

    ];

    var options = {
        griddiv: 'persons',
        table: 'peronsTable',
        dataType: 'json',
        service: 'persons',
        colNames: colNames,
        colModel: colModel,
        sortname: 'firstName',
        editType: 'popup',
        formOptions: {
            addUrl: '/persons/new',
            editUrl: '/persons/'
        }
    };

    zz.grid.create(options);

});
