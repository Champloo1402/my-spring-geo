$(document).ready(function(){
    $("#login_button").click(function() {
        var login = $("#login").val();
        var password = $("#login").val();

        localStorage.setItem("login", login);
        localStorage.setItem("password", password);
    });

    tryAuth();

    $.getJSON({
        'url': '/admin/count',
        'otherSettings': '',
        'beforeSend': function (xhr) {
            var login = localStorage["login"];
            var password = localStorage["password"];

            if (!isEmpty(login))
                xhr.setRequestHeader("Authorization", "Basic " + encodeBase64(login + ":" + password));
        },
        'success': function (data) {
            var pageCount = (data.count / data.pageSize) + (data.count % data.pageSize > 0 ? 1 : 0);
            var i;

            for (i = 1; i <= pageCount; i++) {
                var id = "page" + i;

                $('#pages').append(
                    $('<li>').attr('class', 'page-item').append(
                        $('<a>').attr('class', 'page-link').attr('id', id).append(i))
                );
            }
        }
    });

    /*$('ul.pages li').click(function(event) {
     console.log(event.target.id);
     loadData(event.target.id);
     });*/

    loadData(0);
});

function tryAuth() {
    var success = false;

    while ( ! success) {
        var login = localStorage["login"];
        var password = localStorage["password"];

        $.getJSON({
            'url': '/admin/count',
            'otherSettings': '',
            'beforeSend': function (xhr) {
                if (!isEmpty(login))
                    xhr.setRequestHeader("Authorization", "Basic " + encodeBase64(login + ":" + password));
            },
            'success': function (result) {
                success = true;
            }
        }).fail(function (jqXHR) {
            if (jqXHR.status == 401) {
                $("#loginModal").modal();
            }
        });
    }
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function loadData(page) {
    $.getJSON({
        'url': '/admin/geo?page=' + page,
        'otherSettings': '',
        'beforeSend': function (xhr) {
            var login = localStorage["login"];
            var password = localStorage["password"];

            if (!isEmpty(login))
                xhr.setRequestHeader("Authorization", "Basic " + encodeBase64(login + ":" + password));
        },
        'success': function (data) {
            var i;

            for (i = 0; i < data.length; i++) {
                $('#data > tbody:last-child').append(
                    $('<tr>')
                        .append($('<td>').append(data[i].ip))
                        .append($('<td>').append(data[i].city))
                        .append($('<td>').append(data[i].region))
                        .append($('<td>').append(data[i].country))
                );
            }
        }
    });
}