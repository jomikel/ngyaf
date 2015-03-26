/**
 * Created by andreas on 2/22/15.
 */

var base = {
    jqgrid: function(options) {

        var $griddiv = jQuery('#griddiv');
        var $tbl = jQuery('<table />').attr({id: options.table}).appendTo($griddiv);
        var $pager = jQuery('<div />').attr({id: options.table + 'Pager'}).appendTo($griddiv);

        if(options.datatype == null)
            options.datatype = 'json';

        options.url = '/services/' + options.service + '/page.' + options.datatype;
        options.pager = options.table + 'Pager';
        options.height = 'auto';
        options.shrinkToFit = true;
        options.autowidth = true;

        var deleteOptions = {
            url: '/services/' + options.service + '/',
            mtype: 'DELETE',
            serializeDelData: function () {
                return ""; // the body MUST be empty in DELETE HTTP requests
            },
            onclickSubmit: function (rp_ge) {
                var rowId = $(this).jqGrid('getGridParam', 'selrow');
                rp_ge.url += rowId;
                return { rr: rowId };
            }
        };

        var editOptions = {
            url: '/services/' + options.service + '/',
            mtype: 'PUT',
            serializeEditData: function(rp_ge) {
                //alert(rp_ge);
                return JSON.stringify(rp_ge);
            },
            ajaxEditOptions: {
                contentType: 'application/json; charset=utf-8'
            }
        };

        var pagerOptions = {
            del: true,
            add: false,
            position: 'left',
            cloneToTop: false
            //search: false,
            //view: false,
        };

        var inlineNavigationOptions = {};

        switch(options.editType) {
            case 'form':
                options.ondblClickRow = function() {
                    var rowId = jQuery('#' + options.table).jqGrid('getGridParam', 'selrow');
                    location.href = '/' + options.service + '/' + rowId;
                };
                pagerOptions.edit = false;
                break;
            case 'popup':
                pagerOptions.edit = true;
                break;
            case 'inline':
                pagerOptions.edit = false;

                inlineNavigationOptions.edit = true;
                inlineNavigationOptions.add = true;
                inlineNavigationOptions.save = true;
                inlineNavigationOptions.cancel = true;
                inlineNavigationOptions.editParams = {};
                inlineNavigationOptions.addParams = {
                    useDefValues: true,
                    addRowParams: {
                        url: '/' + options.service,
                        mtype: 'post'
                    }
                };

                break;
            default:
                // error
                break;

        }

        jQuery($tbl).jqGrid(options);
        jQuery($tbl).jqGrid('navGrid', '#'+options.pager, pagerOptions, editOptions, {}, deleteOptions);
        if (options.editType == 'inline')
            jQuery($tbl).jqGrid('inlineNav', '#'+options.pager, inlineNavigationOptions);

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
    websocket.onopen = function(evt) { onOpen(evt) };
    websocket.onclose = function(evt) { onClose(evt) };
    websocket.onmessage = function(evt) { onMessage(evt) };
    websocket.onerror = function(evt) { onError(evt) };
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