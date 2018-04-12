$(document).ready(function(){
    $.getJSON('/admin/count', function(data) {
        var pageCount = (data.count / data.pageSize) + (data.count % data.pageSize > 0 ? 1 : 0);
        var i;

        for (i = 0; i < pageCount; i++) {
            var id = "page"+i;

            $('#content ul').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id',id).append(
                        i
                    )));
        }
    });
});