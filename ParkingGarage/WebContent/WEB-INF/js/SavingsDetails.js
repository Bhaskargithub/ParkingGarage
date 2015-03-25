var mc_table;
var row_to_be_deleted;
var display_length = 10;

$.extend($.fn.dataTableExt.oStdClasses, {
    sPageButtonActive: "active",
    sPageButton:"paginate_button",
    sFilterInput:"form-control input-group"
});

$(document).ready(function(){

    /********************************************************************/
    /*** Message Center table with values - start ***/

    var json_file_location = 'SavingsDetails.json';

    var dataSet = [];



    $.ajax({
        url: json_file_location,
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log("Inside ajax");
            for (var i = 0; i < data.length; i++) {
                dataSet.push([data[i].UserName, data[i].AccountNumber, data[i].TransactionNumber, data[i].Deposit, data[i].TotalSaving ,data[i].MonthlyEMI, data[i].EMITotalBalance, data[i].PaymentMonth]); //
            }
        },
        error:function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        }
    });

    var counter_abc = 1;

    mc_table = $('#savings_details').dataTable( {
        "data": dataSet,
        "order": [[7, 'desc']],
        "iDisplayLength": display_length,
        "dom": '<fip>rt<p>',
        "bSortClasses": true,
        "bAutoWidth": false,
        "aoColumnDefs": [
            { "sClass": "rightAligner", "aTargets": [ 3, 4, 5, 6 ] }
        ],
        "aoColumns": [
            { "aTargets": [0], "sWidth": "15%" },
            { "aTargets": [1], "sWidth": "15%" },
            { "aTargets": [2], "sWidth": "10%" },
            { "aTargets": [3], "sWidth": "10%" },
            { "aTargets": [4], "sWidth": "10%" },
            { "aTargets": [5], "sWidth": "10%" },
            { "aTargets": [6], "sWidth": "10%" },
            { "aTargets": [7], "sWidth": "10%" }
        ],
        "bStateSave": true,
        "fnStateSave": function (oSettings, oData) {
            console.log("oData" + oData);
            localStorage.setItem('offersDataTables', JSON.stringify(oData));
        },
        "oLanguage": {
            "sInfo": ' _START_-_END_ of _TOTAL_',
            "sInfoEmpty": 'No entries to show',
            "sInfoFiltered": '( Total _MAX_ records )',
            "sSearch": ""
        }

    });
});