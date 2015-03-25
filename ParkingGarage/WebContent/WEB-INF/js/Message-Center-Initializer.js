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

    var json_file_location = 'Test_data.json';



    var dataSet = [];

    $.ajax({
        url: json_file_location,
        async: false,
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                console.log("Data :" + data[i].Status) ;
                dataSet.push([null, data[i].Type, data[i].Sender, data[i].Subject, data[i].date ,data[i].Message, data[i].TO, data[i].BCC, null, data[i].Status]); //
            }
        },
        error:function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);}
    });

    var counter_abc = 1;

    mc_table = $('#message_center').dataTable( {
        "data": dataSet,
        "order": [[4, 'desc']],
        "iDisplayLength": display_length,
        //"dom": '<fi>rt<p>',
        "dom": '<fip>rt<p>',
        "bSortClasses": false,
        "bAutoWidth": false,
        "aoColumnDefs": [
            { "sClass": "hider", "aTargets": [ 1, 5, 6, 7, 9 ] },
            { "sClass": "mail_opener", "aTargets": [ 2, 3, 4 ] },
            {
                "bSortable": false,
                "aTargets":[8],
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
                {
                    $(nTd).css('text-align', 'center');
                },
                "mData": null,
                "mRender": function( data, type, full) {
                    return '<td><a class="delete invisible" data-target="#deleteModal" data-toggle="modal"><i class="ui-tooltip fa fa-trash-o" style="font-size: 18px;" data-original-title="delete" title="Delete mail"></i></a></td>';
                }
            },
            {
                "bSortable": false,
                "aTargets":[0],
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol)
                {
                    $(nTd).css('text-align', 'center');
                },
                "mData": null,
                "mRender": function( data, type, full) {
                    return '<td><input type="checkbox" class="editor-active"></td>';
                }
            }
        ],
        "aoColumns": [
            { "aTargets": [0], "sWidth": "5%" },
            null,
            { "aTargets": [2], "sWidth": "15%" },
            { "aTargets": [3], "sWidth": "60%" },
            { "aTargets": [4], "sWidth": "15%" },
            null,
            null,
            null,
            { "aTargets": [8], "sWidth": "5%" },
            null
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

    mc_table.$('td.mail_opener').attr('data-target','#largeModal').attr('data-toggle','modal');



    $('.dataTables_wrapper .dataTables_filter td').first().remove();
    $('.dataTables_wrapper .dataTables_filter label').addClass("abc");

    $('.dataTables_wrapper .dataTables_filter td').last().addClass('input-group').append('<span class="input-group-addon">' +
                    '<i class="ui-tooltip fa fa-search"></i>' +
                '</span>');


    $('#message_center tbody > tr').each(function(){
        var status = $('td', $(this)).eq(9).html();
        if( status == "read") {
            $(this).css('backgroundColor','#d3d3d3').css('font-weight','normal').css('font-size', '95%');
        } else {
            $(this).css("font-weight","bold");
        }
    });

    $('#message_center tbody').on('click','tr a.delete', function(){

        var target_row = $(this).closest("tr").get(0);
        var from = $('td', target_row).eq(2).html();
        $('#from_address').html("Do you really want to delete the mail from <a>" + from + "</a> ?");
        $('#No').show();
        $('#Yes').show();
        row_to_be_deleted = target_row;
    });

    $('#message_center tbody').on('click','td.mail_opener', function () {

        var target_row = $(this).closest("tr").get(0);
        var from = $('td', target_row).eq(2).html();
        var subject = $('td', target_row).eq(3).html();
        var date = $('td', target_row).eq(4).html();
        var content = $('td', target_row).eq(5).html();
        var to = $('td', target_row).eq(6).html();
        var cc = $('td', target_row).eq(7).html();

        $('#mail_subject').html("<h4>" + subject + "</h4>");
        $('#mail_from').html("<b style='font-size:13px;'>From:</b> <a style='font-size:13px; color:royalblue;'>" + from + "</a>");
        $('#mail_to').html("<b style='font-size:13px;'>To:</b> <a style='font-size:13px; color:royalblue;'>" + to + "</a>");
        $('#mail_cc').html("<b style='font-size:13px;'>Cc:</b> <a style='font-size:13px; color:royalblue;'>" + cc + "</a>");
        $('#mail_date').html("<b style='font-size:13px;'>Date:</b> " + date );
        $('#mail_body').html(content);

        $(target_row).css('backgroundColor','#d3d3d3').css('font-weight','normal');
    } );

    $('.delete-icon').on('click', function(){

        var aTrs = mc_table.fnGetNodes();
        var flag=0;
        for ( var i=0 ; i<aTrs.length ; i++ )
        {
            if($('input:checked', aTrs[i]).val()){
                flag=1;
                $('#from_address').html("Do you really want to delete the selected mails?");
                $('#No').show();
                $('#Yes').show();
                break;
            }
        }

        if(flag==0) {
            $('#from_address').html("No mail has been selected, please select at least one mail to delete");
            $('#No').hide();
            $('#Yes').hide();
        }
    });

    $('#select_all').on('click', function () {

        var row = mc_table.fnGetNodes();
        var page_num = Math.ceil(mc_table.fnSettings()._iDisplayStart / mc_table.fnSettings()._iDisplayLength);

        if (!this.checked) {
            for (var i = 0 + (page_num * display_length), j = 0 ; j < display_length ; j++, i++) {
                $('input:checkbox', row[i]).prop('checked',false);
            }
        } else {
            for (var i = 0 + (page_num * display_length), j = 0 ; j < display_length ; j++, i++) {
                $('input:checkbox', row[i]).prop('checked',true);
            }
        }
    });



});




function deleterows(source_text){

    if(source_text == "Do you really want to delete the selected mails?") {
        var aTrs = mc_table.fnGetNodes();
        var flag=0;
//        if(mc_table.fnSettings()._iDisplayStart != 0) {
//            mc_table.fnSettings().iInitDisplayStart = mc_table.fnSettings()._iDisplayStart - mc_table.fnSettings()._iDisplayLength;
//            console.log("mc_table.fnSettings().iInitDisplayStart" + mc_table.fnSettings().iInitDisplayStart)
//        }
        for ( var i= 0, j=0 ; i<aTrs.length ; i++ )
        {
            if($('input:checked', aTrs[i]).val()){
                j++;
                if(mc_table.fnSettings()._iDisplayStart >= mc_table.fnSettings()._iDisplayLength) {
                    mc_table.fnSettings().iInitDisplayStart = mc_table.fnSettings()._iDisplayStart;
                    if( (mc_table.fnSettings()._iDisplayStart + j) == aTrs.length) {
                        mc_table.fnSettings().iInitDisplayStart = mc_table.fnSettings()._iDisplayStart - mc_table.fnSettings()._iDisplayLength;
                    }
                }
                mc_table.fnDeleteRow(aTrs[i]);
            }
        }

        $('#select_all').attr('checked',false);
    }  else {

        if(mc_table.fnSettings()._iDisplayStart >= mc_table.fnSettings()._iDisplayLength) {

            console.log("mc_table.fnSettings().fnRecordsTotal(" + mc_table.fnSettings().fnRecordsTotal());
            if(row_to_be_deleted.rowIndex == 1 && (row_to_be_deleted.rowIndex + mc_table.fnSettings()._iDisplayStart) == mc_table.fnSettings().fnRecordsTotal() ) {
                mc_table.fnSettings().iInitDisplayStart = mc_table.fnSettings()._iDisplayStart - mc_table.fnSettings()._iDisplayLength;
            } else {
                mc_table.fnSettings().iInitDisplayStart = mc_table.fnSettings()._iDisplayStart;
            }
        }

        var aPos = mc_table.fnGetPosition(row_to_be_deleted);
        mc_table.fnDeleteRow(aPos);


    }
}
