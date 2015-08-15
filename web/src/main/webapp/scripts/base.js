/**
 * Created by andreas on 2/22/15.
 */

var base = {
    jqgrid: function (options) {

        var $griddiv = jQuery('#griddiv');
        var $tbl = jQuery('<table />').attr({id: options.table}).appendTo($griddiv);
        var $pager = jQuery('<div />').attr({id: options.table + 'Pager'}).appendTo($griddiv);

        if (options.datatype == null)
            options.datatype = 'json';

        options.url = '/services/' + options.service + '/page.json';
        options.pager = options.table + 'Pager';
        options.height = 'auto';
        options.shrinkToFit = true;
        options.autowidth = true;
        options.serializeRowData = function (data) {
            delete data.oper;
            if(data.id.substr(0, 3) == "jqg")
                delete data.id;
            return data;
        };

        // only for form popup editing
        var addOptions = {
            url: '/services/' + options.service,
            mtype: 'PUT',
            serializeEditData: function (data) {
                delete data.oper;
                if(data.id == "_empty")
                    delete data.id;
                return data;
            }

        };

        var deleteOptions = {
            url: '/services/' + options.service + '/',
            mtype: 'DELETE',
            serializeDelData: function () {
                return ""; // the body MUST be empty in DELETE HTTP requests
            },
            onclickSubmit: function (rp_ge) {
                var rowId = $(this).jqGrid('getGridParam', 'selrow');
                rp_ge.url += rowId;
                return {rr: rowId};
            }
        };

        var editOptions = {
            url: '/services/' + options.service,
            mtype: 'PUT',
            serializeEditData: function (data) {
                return data;
            }
        };

        var pagerOptions = {
            del: true,
            add: false,
            position: 'left',
            cloneToTop: false,
            search: false,
            view: false
        };

        var inlineNavigationOptions = {};

        switch (options.editType) {
            case 'form':
                options.ondblClickRow = function () {
                    var rowId = jQuery('#' + options.table).jqGrid('getGridParam', 'selrow');
                    location.href = '/' + options.service + '/' + rowId;
                };
                pagerOptions.add = true;
                pagerOptions.addfunc = function() {
                    location.href = '/' + options.service + '/new';
                };
                pagerOptions.edit = true;
                pagerOptions.editfunc = function() {
                    var rowId = jQuery('#' + options.table).jqGrid('getGridParam', 'selrow');
                    location.href = '/' + options.service + '/' + rowId;
                };
                break;

            case 'popup':
                options.editurl = '/services/' + options.service + '.json';
                options.ondblClickRow = function() {
                    jQuery('#edit_table').click();
                };
                pagerOptions.edit = true;
                pagerOptions.add = true;
                break;

            case 'inline':
                pagerOptions.edit = false;
                options.ondblClickRow = function() {
                    jQuery('#table_iledit').click();
                };

                inlineNavigationOptions.edit = true;
                inlineNavigationOptions.add = true;
                inlineNavigationOptions.save = true;
                inlineNavigationOptions.cancel = true;
                inlineNavigationOptions.editParams = {
                    url: '/services/' + options.service + '.json',
                    mtype: 'put'
                };
                inlineNavigationOptions.addParams = {

                    useDefValues: true,
                    addRowParams: {
                        url: '/services/' + options.service + '.json',
                        mtype: 'put'

                    }
                };

                break;

            default:
                // error
                break;

        }

        jQuery($tbl).jqGrid(options);
        jQuery($tbl).jqGrid('navGrid', '#' + options.pager, pagerOptions, editOptions, addOptions, deleteOptions);
        if (options.editType == 'inline')
            jQuery($tbl).jqGrid('inlineNav', '#' + options.pager, inlineNavigationOptions);

    }
};

// only for websocket tests
function send() {
    var input = jQuery('#input').val();
    websocket.send(input);
}


// only for websocket tests
function onInit() {
    var wsUri = 'ws://localhost:8080/websocket/myHandler';
    websocket = new WebSocket(wsUri);
    websocket.onopen = function (evt) {
        onOpen(evt)
    };
    websocket.onclose = function (evt) {
        onClose(evt)
    };
    websocket.onmessage = function (evt) {
        onMessage(evt)
    };
    websocket.onerror = function (evt) {
        onError(evt)
    };
}

// only for websocket tests
function onOpen(evt) {

}

// only for websocket tests
function onClose(evt) {

}

// only for websocket tests
function onMessage(evt) {
    jQuery('#input').val(evt.data);
}

// only for websocket tests
function onError(evt) {

}