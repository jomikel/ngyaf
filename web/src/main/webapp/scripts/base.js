/**
 * Created by andreas on 2/22/15.
 */

var zz = {
        grid: {
            restDataUrl: null,
            restDeleteUrl: null,
            restAddUrl: null,
            restEditUrl: null,
            formAddUrl: null,
            formEditUrl: null,

            create: function (options) {

                zz.grid.restDeleteUrl = '/services/' + options.service + '/';
                zz.grid.restAddUrl = '/services/' + options.service;
                zz.grid.restEditUrl = '/services/' + options.service;
                zz.grid.formAddUrl = '/services/' + options.service + '/new';
                zz.grid.formEditUrl = '/services/' + options.service + '/';
                zz.grid.restDataUrl = '/services/' + options.service + '/page.' + options.dataType;

                var $griddiv = $('#' + options.griddiv);
                var $tbl = $('<table />').attr({id: options.table}).appendTo($griddiv);
                $('<div />').attr({id: options.table + 'Pager'}).appendTo($griddiv);

                if (options.dataType == null)
                    options.datatype = 'json';
                else
                    options.datatype = options.dataType;

                options.url = zz.grid.restDataUrl;
                options.pager = options.table + 'Pager';
                options.height = 'auto';
                options.shrinkToFit = true;
                options.autowidth = true;
                options.serializeRowData = zz.grid.stripRowData;

                // only for form popup editing
                var addOptions = {
                    url: zz.grid.restAddUrl,
                    mtype: 'PUT',
                    serializeEditData: zz.grid.stripRowData
                };

                var deleteOptions = {
                    url: zz.grid.restDeleteUrl, // default URL is /services/$service/
                    mtype: 'DELETE', // default HTTP method is DELETE
                    serializeDelData: function () {
                        return ""; // the body MUST be empty in DELETE HTTP requests
                    },
                    onclickSubmit: function (rp_ge) {
                        var rowId = $tbl.jqGrid('getGridParam', 'selrow');
                        rp_ge.url += rowId;
                        //return {rr: rowId};
                    }
                };
                // set custom delete options if is set
                if (options.deleteOptions) {

                    // set custom HTTP method if is set
                    if (options.deleteOptions.method)
                        deleteOptions.mtype = options.deleteOptions.method;
                    // set custom DELETE URL if is set
                    if (options.deleteOptions.deleteUrl)
                        deleteOptions.url = options.deleteOptions.deleteUrl;
                }

                var editOptions = {
                    url: zz.grid.restEditUrl,
                    mtype: 'PUT'
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
                            var rowId = $tbl.jqGrid('getGridParam', 'selrow');
                            location.href = options.formOptions.editUrl + rowId;
                        };
                        pagerOptions.add = true;
                        pagerOptions.addfunc = function () {
                            location.href = options.formOptions.addUrl;
                        };
                        pagerOptions.edit = true;
                        pagerOptions.editfunc = function () {
                            var rowId = $tbl.jqGrid('getGridParam', 'selrow');
                            location.href = options.formOptions.editUrl + rowId;
                        };
                        break;

                    case 'popup':
                        options.editurl = zz.grid.restEditUrl + '.' + options.dataType;
                        options.ondblClickRow = function () {
                            $('#edit_' + options.table).click();
                        };
                        pagerOptions.edit = true;
                        pagerOptions.add = true;
                        break;

                    case 'inline':
                        pagerOptions.edit = false;
                        options.ondblClickRow = function () {
                            $('#' + options.table + '_iledit').click();
                        };

                        inlineNavigationOptions.edit = true;
                        inlineNavigationOptions.add = true;
                        inlineNavigationOptions.save = true;
                        inlineNavigationOptions.cancel = true;
                        inlineNavigationOptions.editParams = {
                            url: zz.grid.restEditUrl,
                            mtype: 'put'
                        };
                        inlineNavigationOptions.addParams = {

                            useDefValues: true,
                            addRowParams: {
                                url: zz.grid.restAddUrl,
                                mtype: 'put',
                                aftersavefunc: function () {
                                    $tbl.trigger("reloadGrid");
                                }
                            }
                        };

                        break;

                    default:
                        // error
                        break;

                }

                $($tbl).jqGrid(options);
                $($tbl).jqGrid('navGrid', '#' + options.pager, pagerOptions, editOptions, addOptions, deleteOptions);
                if (options.editType == 'inline')
                    $($tbl).jqGrid('inlineNav', '#' + options.pager, inlineNavigationOptions);

            },

            stripRowData: function (data) {
                if (data.oper)
                    delete data.oper;

                if (data.id) {
                    if (data.id == '_empty' || data.id.substr(0, 3) == "jqg")
                        delete data.id;
                }

                return data;
            }
        }
    }
    ;

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