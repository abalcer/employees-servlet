$(document).ready(function() {
    var currentEmployeeUrl = "/employee";
    var templates = {};

    function loadEmployees(url) {
        currentEmployeeUrl = url;
        $.getJSON(url).done(function(data) {
            var html = templates.employee(data);
            $('#contentBody').html(html);
        });
    }

    function objectifyForm(formArray) {//serialize data function
          var result = {};
          for (var i = 0; i < formArray.length; i++){
            result[formArray[i]['name']] = formArray[i]['value'];
          }
          return result;
        }

    function precompileTemplate() {
        var promises = [];
        ['employee', 'edit'].forEach(function(tpl) {
            promises.push(
                $.ajax("/templates/" + tpl + ".html")
                    .done(function(source) {
                        templates[tpl] = Tangular.compile(source);
                    }));
        });
        return $.when.apply($, promises);
    }

    $(document).on('click', '.pager a', function(e){
        e.preventDefault();
        var url = $(this).attr("href");
        loadEmployees(url);
    });

    $(document).on('click', 'a.edit', function(e){
        e.preventDefault();
        var promises = [$.getJSON('/department')];
        if($(this).data("id")) {
            promises.push($.getJSON("/employee/" + $(this).data("id")));
        } else {
            promises.push($.Deferred().resolve([{}]));
        }

        $.when.apply($, promises).then(function(departmentRes, employeeResp){
            var html = templates.edit({employee: employeeResp[0], departments: departmentRes[0]});
            $('#employeeEditModal .modal-body').html(html);
        });
        $('#employeeEditModal').modal("show");
    });

     $(document).on('click', '#employeeEditModal .btn-primary', function(e) {
            $('#formErrors').html('');
            var form = $("form");
            var url = form.attr('action');
            var data = JSON.stringify(objectifyForm(form.serializeArray()));
            $.ajax(url, {
                data: data,
                contentType : 'application/json',
                type : 'POST'
            }).done(function(data){
                $('#employeeEditModal').modal("hide");
                loadEmployees(currentEmployeeUrl);
            }).fail(function(jqXHR, textStatus, errorThrown){
                var html = Tangular.render('<div class="row"><ul class="col-sm-offset-3">{{foreach error in errors}}<li class="text-danger">{{error.defaultMessage}}</li>{{end}}</ul></div>',
                    jqXHR.responseJSON);
                $('#formErrors').html(html);
            });
        });

    precompileTemplate().then(function() {
        loadEmployees("/employee");
    });
});