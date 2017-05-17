/**
 * Created by dzianismitrakhovich on 16.05.17.
 */

$(document).ready(function () {
    $('#deleteBtn').click(function () {
        deleteItem();
    })
});

    function deleteItem() {

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var orderId = $("#orderId").val();
        var courseId = $("#courseId").val();
        $.ajax({
            url: '/orders/' + orderId + '/courses/' + courseId,
            type: 'DELETE',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                // Do something with the result
            }
        })
    }
