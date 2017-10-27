$(document).ready(function(){
    var currentEmployeeUrl = "/employee";

    function loadEmployees(url) {
        currentEmployeeUrl = url;
        $.ajax(url).done(function(html) {
            $('#employeeContainer').html(html);
        });
    }

    $(document).on('click', '.pager a', function(e){
        e.preventDefault();
        var url = $(this).attr("href");
        loadEmployees(url);
    });

    $(document).on('click', 'a.edit', function(e){
        e.preventDefault();
        var url = $(this).attr("href");
        $.ajax(url).done(function(html) {
            $('#employeeEditModal .modal-body').html(html);
        });
        $('#employeeEditModal').modal("show");
    });

    $(document).on('click', 'a.delete', function(e) {
        e.preventDefault();
        var url = $(this).attr("href");

        bootbox.confirm({
            message: "Do you really wants to delete the employee?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result) {
                    $.ajax({
                        url: url,
                        method: 'delete'
                    }).done(function() {
                        loadEmployees(currentEmployeeUrl);
                    });
                }
            }
        });
    });

    $(document).on('click', '#employeeEditModal .btn-primary', function(e) {
        var form = $("form");
        var url = form.attr('action');
        $.post(url, form.serialize(), function(data) {
            if(data == "OK") {
                $('#employeeEditModal').modal("hide");
                loadEmployees(currentEmployeeUrl);
            } else {
                $('#employeeEditModal .modal-body').html(data);
            }
        });
    });

    loadEmployees(currentEmployeeUrl);
});