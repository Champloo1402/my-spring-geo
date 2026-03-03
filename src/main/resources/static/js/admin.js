var currentCity = '';

$(document).ready(function(){
    loadPages();
    loadData(0);
});

function search() {
    currentCity = $('#citySearch').val();
    loadPages();
    loadData(0);
}

function clearSearch() {
    currentCity = '';
    $('#citySearch').val('');
    loadPages();
    loadData(0);
}

function loadPages() {
    $('#pages').empty();

    $.getJSON('/admin/count?city=' + currentCity, function(data) {
        var pageCount = Math.ceil(data.count / data.pageSize);
        var i;

        for (i = 1; i <= pageCount; i++) {
            $('#pages').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i - 1).append('Page ' + i))
            );
        }
    });

    $("#pages").on("click", ".page-link", function(event) {
        loadData(event.target.id);
    });
}

function loadData(page) {
    $("#data > tbody").empty();

    $.getJSON('/admin/geo?page=' + page + '&city=' + currentCity, function(data) {
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
    });
}