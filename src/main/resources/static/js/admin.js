$(document).ready(function(){
    loadPages();
    loadData(0);
});

function loadPages() {
    $.getJSON('/admin/count', function(data) {
        var pageCount = (data.count / data.pageSize) + (data.count % data.pageSize > 0 ? 1 : 0);
        var i;

        for (i = 1; i <= pageCount; i++) {
            var id = "page" + i;

            $('#pages').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', id).append('Page ' + i))
            );
        }
    });

    $('#pages li').click(function(event) {
        console.log(event.target.id);
        loadData(event.target.id);
    });
}

function loadData(page) {
    $.getJSON('/admin/geo?page=' + page, function(data) {
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